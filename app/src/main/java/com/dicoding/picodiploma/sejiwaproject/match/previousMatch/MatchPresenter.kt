package com.dicoding.picodiploma.sejiwaproject.match.previousMatch

import com.dicoding.picodiploma.sejiwaproject.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.api.TheSportDBApi
import com.dicoding.picodiploma.sejiwaproject.model.previousMatch.MatchsResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchPresenter(
    private val view: MatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson) {
    fun getPreviousMatch(id: String) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getPreviousMatchs(id)),
                MatchsResponse::class.java
            )
            uiThread {
                view.hideLoading()
                view.showPreviousMatch(data.events)
            }
        }
    }
}