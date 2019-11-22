package com.dicoding.picodiploma.sejiwaproject.features.team

import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.sejiwaproject.R
import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.commons.utils.invisible
import com.dicoding.picodiploma.sejiwaproject.db.database
import com.dicoding.picodiploma.sejiwaproject.features.player.GridPlayerFragment
import com.dicoding.picodiploma.sejiwaproject.features.team.detail.TeamFragment
import com.dicoding.picodiploma.sejiwaproject.features.team.model.Team
import com.google.gson.Gson
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.activity_team_detail.*

class TeamActivity : AppCompatActivity(), TeamView {
    private lateinit var team: Team
    private lateinit var presenter: TeamPresenter
    private var isFavorite: Boolean = false
    private var menuItem: Menu? = null
    private lateinit var teamId: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        teamId = intent.getStringExtra(EXTRA_TEAM) as String

        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamPresenter(
            this,
            request,
            gson,
            database
        )
        presenter.getDetailTeam(teamId)
        presenter.favoriteState(teamId)

        val sectionsPagerAdapter =
            TeamPagerAdapter(
                supportFragmentManager
            )
        sectionsPagerAdapter.populateFragment(
            TeamFragment.newInstance(
                teamId
            ), "About Team"
        )
        sectionsPagerAdapter.populateFragment(
            GridPlayerFragment.newInstance(
                teamId
            ), "Player"
        )
        view_pager_team.adapter = sectionsPagerAdapter
        view_pager_team.offscreenPageLimit = 2
        tabs.setupWithViewPager(view_pager_team)

        detail_toolbar.title = "Detail Team"
        setSupportActionBar(detail_toolbar)

        collapsing.setExpandedTitleColor(Color.TRANSPARENT)
        collapsing.setCollapsedTitleTextColor(Color.WHITE)
    }

    companion object {
        const val EXTRA_TEAM = "extra_team"
    }

    override fun showDetailTeam(data: Team) {
        if (data.idTeam.isNullOrEmpty()) {
            name_team.invisible()
            team_stadium.invisible()
            team_logo.invisible()
        } else {
            team = Team(
                idTeam = data.idTeam,
                teamName = data.teamName,
                teamBadge = data.teamBadge,
                teamDescription = data.teamDescription,
                teamFormed = data.teamFormed,
                teamJersey = data.teamJersey,
                teamLogo = data.teamLogo,
                teamStadium = data.teamStadium
            )
            name_team.text = data.teamName

            Glide.with(this)
                .load(data.teamStadium)
                .apply(RequestOptions().override(550, 550))
                .apply(RequestOptions.bitmapTransform(BlurTransformation(20, 3)))
                .into(team_stadium)

            Glide.with(this)
                .load(data.teamLogo)
                .apply(RequestOptions().override(550, 550))
                .into(team_logo)
        }
    }

    override fun favoriteState(isFavorite: Boolean) {
        this.isFavorite = isFavorite
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
                if (isFavorite) {
                    presenter.removeFromFavorite(teamId)
                } else {
                    presenter.addToFavorite(team)
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
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_star_white)
        else
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_star_border_white)
    }
}
