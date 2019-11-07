package com.dicoding.picodiploma.sejiwaproject.features.league.list

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.sejiwaproject.R

class LeagueListActivity : AppCompatActivity() {
    private lateinit var rvLeague: RecyclerView
    private var list: ArrayList<League> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvLeague = findViewById(R.id.rv_league)
        rvLeague.setHasFixedSize(true)

        initData()
        showRecyclerList()
    }


    private fun showRecyclerList() {
        rvLeague.layoutManager = LinearLayoutManager(this)
        val listLeagueAdapter =
            LeagueAdapter(list)
        rvLeague.adapter = listLeagueAdapter
    }

    private fun initData() {
        val id = resources.getStringArray(R.array.leagueID)
        val name = resources.getStringArray(R.array.league)
        val location = resources.getStringArray(R.array.leagueLocation)
        val logo = resources.obtainTypedArray(R.array.leagueLogo)

        for (i in id.indices) {
            list.add(
                League(
                    id[i],
                    name[i],
                    location[i],
                    logo.getResourceId(i, 0)
                )
            )
        }
        logo.recycle()
    }
}

