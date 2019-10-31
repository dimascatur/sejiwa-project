package com.dicoding.picodiploma.sejiwaproject.features.match.searchMatch

import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.commons.api.TheSportDBApi
import com.dicoding.picodiploma.sejiwaproject.features.match.searchMatch.model.SearchMatchResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class SearchMatchPresenter (private val view: SearchMatchView,
                            private val apiRepository: ApiRepository,
                            private val gson: Gson) {

    fun getSearchMatch(id: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getSearchMatch(id)),
                SearchMatchResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showSearchList(data.event ?: listOf())
            }
        }
    }
}