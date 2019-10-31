package com.dicoding.picodiploma.sejiwaproject.features.match.nextMatch

import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.commons.api.TheSportDBApi
import com.dicoding.picodiploma.sejiwaproject.features.match.nextMatch.model.NextMatchResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class NextMatchPresenter (
    private val view: NextMatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson) {
    fun getNextMatch(id: String) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getNextMatchs(id)),
                NextMatchResponse::class.java
            )
            uiThread {
                view.hideLoading()
                view.showNextMatch(data.events)
            }
        }
    }
}