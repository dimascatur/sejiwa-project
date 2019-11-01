package com.dicoding.picodiploma.sejiwaproject.features.match.search

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
import com.dicoding.picodiploma.sejiwaproject.commons.utils.invisible
import com.dicoding.picodiploma.sejiwaproject.commons.utils.visible
import com.dicoding.picodiploma.sejiwaproject.features.match.search.model.SearchMatch
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_search_match.*

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
        progress_bar.invisible()
    }


    override fun showLoading() {
        progress_bar.visible()
    }

    override fun hideLoading() {
        progress_bar.invisible()
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
                if (query.isNotEmpty())
                    presenter.getSearchMatch(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return true
            }

        })

        val searchMenuItem = menu.findItem(R.id.search_match)
        searchMenuItem.expandActionView()
        return true
    }
}
