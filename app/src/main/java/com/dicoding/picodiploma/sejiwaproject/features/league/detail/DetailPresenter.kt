package com.dicoding.picodiploma.sejiwaproject.features.league.detail

import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.commons.api.TheSportDBApi
import com.dicoding.picodiploma.sejiwaproject.commons.utils.CoroutineContextProvider
import com.dicoding.picodiploma.sejiwaproject.features.league.detail.model.LeagueResponse
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailPresenter (private val view: DetailView,
                       private val apiRepository: ApiRepository,
                       private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getDetailList(id: String?) {
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository
                .doRequestAsync(TheSportDBApi.getDetails(id)).await(),
                LeagueResponse::class.java
            )

                view.showDetailList(data.leagues)
            }
        }
    }

