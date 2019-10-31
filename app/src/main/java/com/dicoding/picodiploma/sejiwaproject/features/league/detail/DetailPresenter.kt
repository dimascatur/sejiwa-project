package com.dicoding.picodiploma.sejiwaproject.features.league.detail

import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.commons.api.TheSportDBApi
import com.dicoding.picodiploma.sejiwaproject.features.league.detail.model.LeagueResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailPresenter (private val view: DetailView,
                       private val apiRepository: ApiRepository,
                       private val gson: Gson) {
    fun getDetailList(id: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getDetails(id)),
                LeagueResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showDetailList(data.leagues)
            }
        }
    }

}