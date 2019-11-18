package com.dicoding.picodiploma.sejiwaproject.features.match.search

import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.commons.api.TheSportDBApi
import com.dicoding.picodiploma.sejiwaproject.commons.utils.CoroutineContextProvider
import com.dicoding.picodiploma.sejiwaproject.features.match.search.model.SearchMatchResponse
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchMatchPresenter (private val view: SearchMatchView,
                            private val apiRepository: ApiRepository,
                            private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getSearchMatch(id: String?) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequestAsync(TheSportDBApi.getSearchMatches(id)).await(),
                SearchMatchResponse::class.java
            )

            val result = data.event?.filter {
                it.sportType == "Soccer"
            }

                view.hideLoading()
                view.showSearchList(result?: listOf())
            }
        }
    }
