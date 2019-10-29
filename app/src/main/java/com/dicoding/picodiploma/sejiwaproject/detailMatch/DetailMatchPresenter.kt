package com.dicoding.picodiploma.sejiwaproject.detailMatch

import com.dicoding.picodiploma.sejiwaproject.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.api.TheSportDBApi
import com.dicoding.picodiploma.sejiwaproject.model.detailMatch.DetailMatchResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailMatchPresenter (private val view: DetailMatchView,
                            private val apiRepository: ApiRepository,
                            private val gson: Gson) {
    fun getDetailMatch(id: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getMatchDetail(id)),
                DetailMatchResponse::class.java
            )
            uiThread {
                view.hideLoading()
                view.showMatchDetail(data.events)
            }
        }
    }
}