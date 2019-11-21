package com.dicoding.picodiploma.sejiwaproject.features.match.standings

import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.commons.api.TheSportDBApi
import com.dicoding.picodiploma.sejiwaproject.commons.utils.CoroutineContextProvider
import com.dicoding.picodiploma.sejiwaproject.features.match.standings.model.StandingsMatchResponse
import com.dicoding.picodiploma.sejiwaproject.features.team.model.TeamResponse
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StandingsMatchPresenter(
    private var view: StandingsMatchView?,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun onDetach() {
        view = null
    }

    fun getStandingsLeague(idLeague: String?) {
        view?.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequestAsync(TheSportDBApi.getStandingsMatch(idLeague)).await(),
                StandingsMatchResponse::class.java
            )

            data.table.map {
                val response = gson.fromJson(
                    apiRepository
                        .doRequestAsync(TheSportDBApi.getTeamDetail(it.teamId)).await(),
                    TeamResponse::class.java
                )
                val result = it.copy()
                result.teamId = response.teams.first().idTeam
                result.teamBadge = response.teams.first().teamLogo

                view?.hideLoading()
                view?.showStandingsList(result)
            }
        }
    }
}