package com.dicoding.picodiploma.sejiwaproject.features.match.detail

import android.database.sqlite.SQLiteConstraintException
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
import com.dicoding.picodiploma.sejiwaproject.db.Favorite
import com.dicoding.picodiploma.sejiwaproject.db.database
import com.dicoding.picodiploma.sejiwaproject.features.match.detail.model.DetailMatch
import com.dicoding.picodiploma.sejiwaproject.features.team.model.Team
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DetailMatchActivity : AppCompatActivity(),
    DetailMatchView {
    private lateinit var match: DetailMatch
    private var matches: MutableList<DetailMatch> = mutableListOf()
    private lateinit var presenter: DetailMatchPresenter
    private var isFavorite: Boolean = false
    private var menuItem: Menu? = null
    private lateinit var matchDetail: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)

        matchDetail = intent.getStringExtra(EXTRA_ID) as String

        favoriteState()
        val request = ApiRepository()
        val gson = Gson()
        presenter =
            DetailMatchPresenter(
                this,
                request,
                gson
            )
        presenter.getDetailMatch(matchDetail)

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

    override fun showMatchDetail(data: List<DetailMatch>) {
        if (data[0].matchTitle.isNullOrEmpty()) {
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
                matchId = data[0].matchId,
                matchTitle = data[0].matchTitle,
                teamHome = data[0].teamHome,
                teamAway = data[0].teamAway,
                dateMatch = data[0].dateMatch
            )

            home_score.text = data[0].homeScore
            away_score.text = data[0].awayScore
            home_goal.text = data[0].homeGoal
            away_goal.text = data[0].awayGoal
            home_red.text = data[0].homeRedCard
            away_red.text = data[0].awayRedCard
            home_yellow.text = data[0].homeYellowCard
            away_yellow.text = data[0].awayYellowCard
            date_match.text = data[0].dateMatch
        }
        match_title.text = data[0].matchTitle
        team_home.text = data[0].teamHome
        team_away.text = data[0].teamAway

        matches.clear()
        matches.addAll(data)
        presenter.getTeamLogo(data[0].homeId, true)
        presenter.getTeamLogo(data[0].awayId, false)

    }

    private fun favoriteState() {
        database.use {
            val result = select(Favorite.TABLE_FAVORITE)
                .whereArgs(
                    "(MATCH_ID = {id})",
                    "id" to matchDetail
                )
            val favorite = result.parseList(classParser<Favorite>())
            if (favorite.isNotEmpty()) isFavorite = true
        }
    }

    override fun showHomeLogo(data: List<Team>) {
        Glide.with(this)
            .load(data[0].teamLogo)
            .into(home_logo)
    }

    override fun showAwayLogo(data: List<Team>) {
        Glide.with(this)
            .load(data[0].teamLogo)
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
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }

            else -> super.onOptionsItemSelected(item)

        }
    }

    private fun addToFavorite() {
        try {
            database.use {
                insert(
                    Favorite.TABLE_FAVORITE,
                    Favorite.MATCH_ID to match.matchId,
                    Favorite.TEAM_HOME to match.teamHome,
                    Favorite.TEAM_AWAY to match.teamAway,
                    Favorite.DATE_MATCH to match.dateMatch
                )
            }
        } catch (e: SQLiteConstraintException) {

        }
    }

    private fun removeFromFavorite() {
        try {
            database.use {
                delete(
                    Favorite.TABLE_FAVORITE, "(MATCH_ID = {id})",
                    "id" to matchDetail
                )
            }
        } catch (e: SQLiteConstraintException) {
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
