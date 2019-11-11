package com.dicoding.picodiploma.sejiwaproject.features.match.detail

import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.commons.api.TheSportDBApi
import com.dicoding.picodiploma.sejiwaproject.features.match.detail.model.DetailMatchResponse
import com.dicoding.picodiploma.sejiwaproject.features.team.model.TeamResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailMatchPresenter(
    private var view: DetailMatchView?,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun onDetach() {
        view = null
    }

    fun getDetailMatch(id: String?) {
        view?.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getMatchDetails(id)),
                DetailMatchResponse::class.java
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
                    view?.hideLoading()
                    view?.matchReady(result)
                }
            }
        }
    }
}