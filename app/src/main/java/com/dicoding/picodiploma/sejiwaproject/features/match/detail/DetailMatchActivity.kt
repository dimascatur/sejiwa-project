package com.dicoding.picodiploma.sejiwaproject.features.match.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.sejiwaproject.R
import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.commons.utils.invisible
import com.dicoding.picodiploma.sejiwaproject.commons.utils.visible
import com.dicoding.picodiploma.sejiwaproject.db.database
import com.dicoding.picodiploma.sejiwaproject.features.match.detail.model.DetailMatch
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_match.*

class DetailMatchActivity : AppCompatActivity(),
    DetailMatchView {
    private lateinit var match: DetailMatch
    private lateinit var presenter: DetailMatchPresenter
    private var isFavorite: Boolean = false
    private var menuItem: Menu? = null
    private lateinit var matchDetail: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)

        matchDetail = intent.getStringExtra(EXTRA_ID) as String

        val request = ApiRepository()
        val gson = Gson()
        presenter =
            DetailMatchPresenter(
                this,
                request,
                gson,
                database
            )
        presenter.getDetailMatch(matchDetail)
        presenter.favoriteState(matchDetail)

    }

    companion object {
        const val EXTRA_ID = "extra_key"
    }

    override fun showLoading() {
        progress_bar.visible()
    }

    override fun hideLoading() {
        progress_bar.invisible()
    }

    override fun favoriteState(isFavorite: Boolean) {
        this.isFavorite = isFavorite

    }

    override fun matchReady(detailMatch: DetailMatch) {
        if (detailMatch.matchTitle.isNullOrEmpty()) {
            home_score.invisible()
            away_score.invisible()
            home_goal.invisible()
            away_goal.invisible()
            home_red.invisible()
            away_red.invisible()
            home_yellow.invisible()
            away_yellow.invisible()
            date_match.invisible()

        } else {
            match = DetailMatch(
                matchId = detailMatch.matchId,
                matchTitle = detailMatch.matchTitle,
                teamHome = detailMatch.teamHome,
                teamAway = detailMatch.teamAway,
                dateMatch = detailMatch.dateMatch,
                badgeHome = detailMatch.badgeHome,
                badgeAway = detailMatch.badgeAway
            )

            home_score.text = detailMatch.homeScore
            away_score.text = detailMatch.awayScore
            home_goal.text = detailMatch.homeGoal
            away_goal.text = detailMatch.awayGoal
            home_red.text = detailMatch.homeRedCard
            away_red.text = detailMatch.awayRedCard
            home_yellow.text = detailMatch.homeYellowCard
            away_yellow.text = detailMatch.awayYellowCard
            date_match.text = detailMatch.dateMatch
        }
        match_title.text = detailMatch.matchTitle
        team_home.text = detailMatch.teamHome
        team_away.text = detailMatch.teamAway

        Glide.with(this)
            .load(detailMatch.badgeHome)
            .into(home_logo)

        Glide.with(this)
            .load(detailMatch.badgeAway)
            .into(away_logo)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite){
                    presenter.removeFromFavorite(matchDetail)
                } else {
                    presenter.addToFavorite(match)
                }

                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)

        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_star_white)
        else
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_star_border_white)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }
}
