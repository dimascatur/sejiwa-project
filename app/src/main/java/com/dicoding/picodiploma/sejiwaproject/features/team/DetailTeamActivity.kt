package com.dicoding.picodiploma.sejiwaproject.features.team

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.sejiwaproject.R
import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.commons.utils.invisible
import com.dicoding.picodiploma.sejiwaproject.features.team.model.Team
import com.google.gson.Gson
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.activity_team_detail.*

class DetailTeamActivity : AppCompatActivity(), DetailTeamView {
    private var teams: MutableList<Team> = mutableListOf()
    private lateinit var presenter: DetailTeamPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        val teamId = intent.getStringExtra(EXTRA_TEAM)

        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailTeamPresenter(
            this,
            request,
            gson
        )
        presenter.getDetailTeam(teamId)

//        val sectionsPagerAdapter =
//            DetailTeamAdapter(
//                supportFragmentManager
//            )
//        sectionsPagerAdapter.populateFragment()
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
