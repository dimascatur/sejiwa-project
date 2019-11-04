package com.dicoding.picodiploma.sejiwaproject.features.match.previous

import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.commons.api.TheSportDBApi
import com.dicoding.picodiploma.sejiwaproject.features.team.model.TeamResponse
import com.dicoding.picodiploma.sejiwaproject.features.match.previous.model.PreviousMatchResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PreviousMatchPresenter(
    private val view: PreviousMatchView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {
    fun getPreviousMatch(id: String) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getPreviousMatches(id)),
                PreviousMatchResponse::class.java
            )

            data.events.map {
                val homeResponse = gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getTeamDetail(it.homeId)),
                    TeamResponse::class.java
                )

                val awayResponse = gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getTeamDetail(it.awayId)),
                    TeamResponse::class.java
                )

                val result = it.copy()
                result.awayId = awayResponse.teams.first().idTeam
                result.homeId = homeResponse.teams.first().idTeam
                result.badgeHome = homeResponse.teams.first().teamLogo
                result.badgeAway = awayResponse.teams.first().teamLogo

                uiThread {
                    view.matchReady(result)
                }
            }
        }
    }
}