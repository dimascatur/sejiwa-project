package com.dicoding.picodiploma.sejiwaproject.detailMatch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.sejiwaproject.R
import com.dicoding.picodiploma.sejiwaproject.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.model.detailMatch.DetailMatch
import com.dicoding.picodiploma.sejiwaproject.model.detailTeam.DetailTeam
import com.dicoding.picodiploma.sejiwaproject.utils.invisible
import com.dicoding.picodiploma.sejiwaproject.utils.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_match.*

class DetailMatchActivity : AppCompatActivity(), DetailMatchView {

    private var matchs: MutableList<DetailMatch> = mutableListOf()
    private lateinit var presenter: DetailMatchPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)

        val matchDetail = intent.getStringExtra(EXTRA_ID)

        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailMatchPresenter(this, request, gson)
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

        matchs.clear()
        matchs.addAll(data)
        presenter.getDetailTeam(data[0].homeId, true)
        presenter.getDetailTeam(data[0].awayId, false)

    }

    override fun showHomeLogo(data: List<DetailTeam>) {
        Glide.with(this)
            .load(data[0].teamLogo)
            .apply(RequestOptions().override(80, 80))
            .into(home_logo)
    }

    override fun showAwayLogo(data: List<DetailTeam>) {
        Glide.with(this)
            .load(data[0].teamLogo)
            .apply(RequestOptions().override(80, 80))
            .into(away_logo)
    }

}
