package com.dicoding.picodiploma.sejiwaproject.features.league.detail

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.sejiwaproject.R
import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.commons.utils.invisible
import com.dicoding.picodiploma.sejiwaproject.features.league.detail.model.League
import com.dicoding.picodiploma.sejiwaproject.features.match.next.NextMatchFragment
import com.dicoding.picodiploma.sejiwaproject.features.match.previous.PreviousMatchFragment
import com.dicoding.picodiploma.sejiwaproject.features.match.search.SearchMatchActivity
import com.dicoding.picodiploma.sejiwaproject.features.match.standings.StandingsMatchFragment
import com.google.gson.Gson
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.league_badge
import kotlinx.android.synthetic.main.activity_detail.league_formed
import kotlinx.android.synthetic.main.activity_detail.league_loc
import kotlinx.android.synthetic.main.activity_detail.league_logo
import kotlinx.android.synthetic.main.activity_detail.name_league
import kotlinx.android.synthetic.main.activity_detail.tabs
import kotlinx.android.synthetic.main.activity_detail.view_pager_detail


class DetailLeagueActivity : AppCompatActivity(),
    DetailView {
    private var leagues: MutableList<League> = mutableListOf()
    private lateinit var presenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setSupportActionBar(detail_toolbar)
        supportActionBar?.title = "Match"

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
            ), "Previous"
        )
        sectionsPagerAdapter.populateFragment(
            NextMatchFragment.newInstance(nameLeague ?: "4328"),
            "Next"
        )

        sectionsPagerAdapter.populateFragment(
            StandingsMatchFragment.newInstance(nameLeague ?: "4328"),
            "Standings"
        )

        view_pager_detail.adapter = sectionsPagerAdapter
        view_pager_detail.offscreenPageLimit = 3
        tabs.setupWithViewPager(view_pager_detail)

        collapsing.setExpandedTitleColor(Color.TRANSPARENT)
        collapsing.setCollapsedTitleTextColor(Color.WHITE)
    }

    companion object {
        const val EXTRA_KEY = "extra_key"
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
                .apply(RequestOptions.bitmapTransform(BlurTransformation(20, 3)))
                .into(league_badge)

            Glide.with(this)
                .load(data[0].teamLogo)
                .apply(RequestOptions().override(550, 550))
                .into(league_logo)
        }

        leagues.clear()
        leagues.addAll(data)

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.search -> {
                val moveIntent = Intent(this@DetailLeagueActivity, SearchMatchActivity::class.java)
                startActivity(moveIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
