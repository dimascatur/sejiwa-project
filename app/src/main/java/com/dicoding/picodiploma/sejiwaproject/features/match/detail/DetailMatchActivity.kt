package com.dicoding.picodiploma.sejiwaproject.features.match.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.sejiwaproject.R
import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.commons.utils.invisible
import com.dicoding.picodiploma.sejiwaproject.commons.utils.visible
import com.dicoding.picodiploma.sejiwaproject.features.match.detail.model.DetailMatch
import com.dicoding.picodiploma.sejiwaproject.features.match.detail.model.LogoTeam
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_match.*

class DetailMatchActivity : AppCompatActivity(),
    DetailMatchView {

    private var matches: MutableList<DetailMatch> = mutableListOf()
    private lateinit var presenter: DetailMatchPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)

        val matchDetail = intent.getStringExtra(EXTRA_ID)

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

        } else {
            home_score.text = data[0].homeScore
            away_score.text = data[0].awayScore
            home_goal.text = data[0].homeGoal
            away_goal.text = data[0].awayGoal
            home_red.text = data[0].homeRedCard
            away_red.text = data[0].awayRedCard
            home_yellow.text = data[0].homeYellowCard
            away_yellow.text = data[0].awayYellowCard
        }
        match_title.text = data[0].matchTitle
        team_home.text = data[0].teamHome
        team_away.text = data[0].teamAway

        matches.clear()
        matches.addAll(data)
        presenter.getTeamLogo(data[0].homeId, true)
        presenter.getTeamLogo(data[0].awayId, false)

    }

    override fun showHomeLogo(data: List<LogoTeam>) {
        Glide.with(this)
            .load(data[0].teamLogo)
            .into(home_logo)
    }

    override fun showAwayLogo(data: List<LogoTeam>) {
        Glide.with(this)
            .load(data[0].teamLogo)
            .into(away_logo)
    }

}
