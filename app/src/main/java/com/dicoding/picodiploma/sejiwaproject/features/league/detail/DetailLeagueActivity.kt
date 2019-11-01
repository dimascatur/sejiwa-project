package com.dicoding.picodiploma.sejiwaproject.features.league.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.sejiwaproject.R
import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.features.match.next.NextMatchFragment
import com.dicoding.picodiploma.sejiwaproject.features.match.previous.PreviousMatchFragment
import com.dicoding.picodiploma.sejiwaproject.features.league.detail.model.League
import com.dicoding.picodiploma.sejiwaproject.commons.utils.invisible
import com.dicoding.picodiploma.sejiwaproject.commons.utils.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail.*

class DetailLeagueActivity : AppCompatActivity(),
    DetailView {
    private var leagues: MutableList<League> = mutableListOf()
    private lateinit var presenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val nameLeague = intent.getStringExtra(EXTRA_KEY)

        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailPresenter(
            this,
            request,
            gson
        )
        presenter.getDetailList(nameLeague)

        val sectionsPagerAdapter =
            DetailPagerAdapter(
                supportFragmentManager
            )

        sectionsPagerAdapter.populateFragment(
            PreviousMatchFragment.newInstance(
                nameLeague ?: "4328"
            ), "Previous Match"
        )
        sectionsPagerAdapter.populateFragment(
            NextMatchFragment.newInstance(nameLeague ?: "4328"),
            "Next Match"
        )
        view_pager_detail.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager_detail)

        supportActionBar?.title = "Detail League"
    }

    companion object {
        const val EXTRA_KEY = "extra_key"
    }

    override fun showLoading() {
        progress_bar.visible()
    }

    override fun hideLoading() {
        progress_bar.invisible()

    }

    override fun showDetailList(data: List<League>) {
        if (data[0].teamName.isNullOrEmpty()) {
            name_league.invisible()
            league_loc.invisible()
            league_formed.invisible()
            league_badge.invisible()
            league_logo.invisible()
        } else {
            name_league.text = data[0].teamName
            league_loc.text = data[0].teamLocation
            league_formed.text = data[0].teamFormed

            Glide.with(this)
                .load(data[0].teamPoster)
                .apply(RequestOptions().override(550, 550))
                .into(league_badge)

            Glide.with(this)
                .load(data[0].teamLogo)
                .apply(RequestOptions().override(550, 550))
                .into(league_logo)
        }

        leagues.clear()
        leagues.addAll(data)

    }
}
