package com.dicoding.picodiploma.sejiwaproject.features.team

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.sejiwaproject.R
import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.commons.utils.invisible
import com.dicoding.picodiploma.sejiwaproject.features.player.GridPlayerFragment
import com.dicoding.picodiploma.sejiwaproject.features.team.detail.TeamFragment
import com.dicoding.picodiploma.sejiwaproject.features.team.model.Team
import com.google.gson.Gson
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.activity_team_detail.*

class TeamActivity : AppCompatActivity(), TeamView {
    private lateinit var presenter: TeamPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        val teamId = intent.getStringExtra(EXTRA_TEAM)

        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamPresenter(
            this,
            request,
            gson
        )
        presenter.getDetailTeam(teamId)

        val sectionsPagerAdapter =
            TeamPagerAdapter(
                supportFragmentManager
            )
        sectionsPagerAdapter.populateFragment(
            TeamFragment.newInstance(
                teamId ?: "133608"
            ), "About Team"
        )
        sectionsPagerAdapter.populateFragment(
            GridPlayerFragment.newInstance(
                teamId ?: "133690"
            ), "Player"
        )
        view_pager_team.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager_team)

        detail_toolbar.title = "Detail Team"

        collapsing.setExpandedTitleColor(Color.TRANSPARENT)
        collapsing.setCollapsedTitleTextColor(Color.WHITE)
    }

    companion object {
        const val EXTRA_TEAM = "extra_team"
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showDetailTeam(data: Team) {
        if (data.idTeam.isNullOrEmpty()) {
            name_team.invisible()
            team_stadium.invisible()
            team_logo.invisible()
        } else {
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
}
