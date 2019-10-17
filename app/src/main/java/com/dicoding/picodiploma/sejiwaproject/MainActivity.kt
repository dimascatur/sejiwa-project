package com.dicoding.picodiploma.sejiwaproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.sejiwaproject.adapter.LeagueAdapter
import com.dicoding.picodiploma.sejiwaproject.data.LeagueData
import com.dicoding.picodiploma.sejiwaproject.model.League
import com.dicoding.picodiploma.sejiwaproject.view.MainActivityUI
import org.jetbrains.anko.setContentView

class MainActivity : AppCompatActivity() {
    private lateinit var rvLeague: RecyclerView
    private var list: ArrayList<League> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)

        rvLeague = findViewById(R.id.rv_league)
        rvLeague.setHasFixedSize(true)

        initData()
        showRecyclerList()
    }


    private fun showRecyclerList() {
        rvLeague.layoutManager = LinearLayoutManager(this)
        val listLeagueAdapter = LeagueAdapter(list)
        rvLeague.adapter = listLeagueAdapter
    }

    private fun initData(){
        val name = resources.getStringArray(R.array.leagueNames)
        val description = resources.getStringArray(R.array.leagueDescription)
        val location = resources.getStringArray(R.array.leagueLocation)
        val logo = resources.obtainTypedArray(R.array.leagueLogo)
        list = name.mapIndexed { index, leagueNames ->
            League().apply {
                this.name = leagueNames
                this.description = description[index]
                this.location = location[index]
                    this.photo = logo.getResourceId(index, 0)

            }
        } as ArrayList<League>

        logo.recycle()
    }
}
