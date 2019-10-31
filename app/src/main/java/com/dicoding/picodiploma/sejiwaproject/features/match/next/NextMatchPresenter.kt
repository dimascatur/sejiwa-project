package com.dicoding.picodiploma.sejiwaproject.features.match.nextMatch

import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.commons.api.TheSportDBApi
import com.dicoding.picodiploma.sejiwaproject.features.match.detailMatch.model.LogoTeamResponse
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

            val nextEvent = data.events.map {
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
                view.showNextMatch(nextEvent)
            }
        }
    }
}