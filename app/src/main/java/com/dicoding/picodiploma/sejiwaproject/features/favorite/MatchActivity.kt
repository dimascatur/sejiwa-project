package com.dicoding.picodiploma.sejiwaproject.features.favorite

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.picodiploma.sejiwaproject.R
import com.dicoding.picodiploma.sejiwaproject.features.league.detail.DetailPagerAdapter
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        val teamMatch = intent.getStringExtra(EXTRA_ID)


        val sectionsPagerAdapter =
            DetailPagerAdapter(
                supportFragmentManager
            )

        sectionsPagerAdapter.populateFragment(
            MatchFragment.newInstance(
                teamMatch ?: "68890"
            ), "Match"
        )
        view_pager_favorite.adapter = sectionsPagerAdapter
        tabs.setupWithViewPager(view_pager_favorite)

        favorite_toolbar.title = "Favorite Match"

        collapsing.setExpandedTitleColor(Color.TRANSPARENT)
        collapsing.setCollapsedTitleTextColor(Color.WHITE)
    }

    companion object {
        const val EXTRA_ID = "extra_id"
    }
}
