package com.dicoding.picodiploma.sejiwaproject.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.sejiwaproject.R
import com.dicoding.picodiploma.sejiwaproject.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.model.Leagues
import com.dicoding.picodiploma.sejiwaproject.ui.main.SectionsPagerAdapter
import com.dicoding.picodiploma.sejiwaproject.utils.invisible
import com.dicoding.picodiploma.sejiwaproject.utils.visible
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail.*

class DetailLeagueActivity : AppCompatActivity(), DetailView {
    private var leagues: MutableList<Leagues> = mutableListOf()
    private lateinit var presenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        val nameLeague = intent.getStringExtra(EXTRA_KEY)

        val request = ApiRepository()
        val gson = Gson()
        presenter = DetailPresenter(this, request, gson)
        presenter.getDetailList(nameLeague)

        val sectionsPagerAdapter =
            SectionsPagerAdapter(
                this,
                supportFragmentManager
            )
        view_pager_detail.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager_detail)

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

    override fun showDetailList(data: List<Leagues>) {
        name_league.text = data[0].teamName
        league_loc.text = data[0].teamLocation
        league_formed.text = data[0].teamFormed

        Glide.with(this)
            .load(data[0].teamPoster)
            .apply(RequestOptions().override(550, 550))
            .into(league_badge)

        leagues.clear()
        leagues.addAll(data)

    }
}
