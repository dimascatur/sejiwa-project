package com.dicoding.picodiploma.sejiwaproject.features.match.searchMatch

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.sejiwaproject.R
import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.features.match.searchMatch.model.SearchMatch
import com.google.gson.Gson

class SearchMatchActivity : AppCompatActivity(),
    SearchMatchView {
    private lateinit var rvMatch: RecyclerView
    private lateinit var presenter: SearchMatchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_match)
        rvMatch = findViewById(R.id.rv_search_match)
        rvMatch.setHasFixedSize(true)

        val request = ApiRepository()
        val gson = Gson()
        presenter =
            SearchMatchPresenter(
                this,
                request,
                gson
            )

    }


    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showSearchList(data: List<SearchMatch>) {
        rvMatch.layoutManager = LinearLayoutManager(this)
        val listMatch =
            SearchMatchAdapter(
                data
            )
        rvMatch.adapter = listMatch

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search_match).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                Toast.makeText(this@SearchMatchActivity, query, Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                presenter.getSearchMatch(newText)
                return true
            }

        })
        return true
    }
}
