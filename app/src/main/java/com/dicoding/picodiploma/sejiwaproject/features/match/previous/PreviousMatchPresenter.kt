package com.dicoding.picodiploma.sejiwaproject.features.match.previous

import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.commons.api.TheSportDBApi
import com.dicoding.picodiploma.sejiwaproject.features.match.detail.model.LogoTeamResponse
import com.dicoding.picodiploma.sejiwaproject.features.match.previous.model.MatchsResponse
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

            val previousEvent = data.events.map {
                val homeResponse = gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getTeamDetail(it.homeId)),
                    LogoTeamResponse::class.java
                )

                val awayResponse = gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getTeamDetail(it.awayId)),
                    LogoTeamResponse::class.java
                )

                val result = it.copy()
                result.badgeHome = homeResponse.teams.first().teamLogo
                result.badgeAway = awayResponse.teams.first().teamLogo

                result
            }
            uiThread {
                view.hideLoading()
                view.showPreviousMatch(previousEvent)
            }
        }
    }
}