package com.dicoding.picodiploma.sejiwaproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvLeague: RecyclerView
    private var list: ArrayList<League> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)

        rvLeague = findViewById(R.id.rv_league)
        rvLeague.setHasFixedSize(true)

        list.addAll(LeagueData.listData)
        showRecyclerList()
    }

    class MainActivityUI : AnkoComponent<MainActivity> {
        override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
            recyclerView {
                id = R.id.rv_league
                layoutManager = LinearLayoutManager(context)
                lparams(width = matchParent, height = matchParent)
                padding = dip(16)
                clipToPadding = false

            }
        }
    }

    private fun showRecyclerList() {
        rvLeague.layoutManager = LinearLayoutManager(this)
        val listLeagueAdapter = LeagueAdapter(list)
        rvLeague.adapter = listLeagueAdapter
    }
}
