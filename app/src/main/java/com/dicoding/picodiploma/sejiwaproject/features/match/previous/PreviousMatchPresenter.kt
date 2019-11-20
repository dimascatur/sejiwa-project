package com.dicoding.picodiploma.sejiwaproject.features.match.previous

import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.commons.api.TheSportDBApi
import com.dicoding.picodiploma.sejiwaproject.commons.utils.CoroutineContextProvider
import com.dicoding.picodiploma.sejiwaproject.features.match.previous.model.PreviousMatchResponse
import com.dicoding.picodiploma.sejiwaproject.features.team.model.TeamResponse
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PreviousMatchPresenter(
    private var view: PreviousMatchView?,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun onDetach() {
        view = null
    }

    fun getPreviousMatch(id: String) {
        view?.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequestAsync(TheSportDBApi.getPreviousMatches(id)).await(),
                PreviousMatchResponse::class.java
            )

            data.events.map {
                val homeResponse = gson.fromJson(
                    apiRepository
                        .doRequestAsync(TheSportDBApi.getTeamDetail(it.homeId)).await(),
                    TeamResponse::class.java
                )

                val awayResponse = gson.fromJson(
                    apiRepository
                        .doRequestAsync(TheSportDBApi.getTeamDetail(it.awayId)).await(),
                    TeamResponse::class.java
                )

                val result = it.copy()
                result.awayId = awayResponse.teams.first().idTeam
                result.homeId = homeResponse.teams.first().idTeam
                result.badgeHome = homeResponse.teams.first().teamLogo
                result.badgeAway = awayResponse.teams.first().teamLogo

                view?.hideLoading()
                view?.matchReady(result)
            }
        }
    }
}