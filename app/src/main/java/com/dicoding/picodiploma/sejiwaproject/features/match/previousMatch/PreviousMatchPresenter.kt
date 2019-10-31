package com.dicoding.picodiploma.sejiwaproject.features.match.previousMatch

import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.commons.api.TheSportDBApi
import com.dicoding.picodiploma.sejiwaproject.features.match.previousMatch.model.MatchsResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PreviousMatchPresenter(
    private val view: PreviousMatchView,
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