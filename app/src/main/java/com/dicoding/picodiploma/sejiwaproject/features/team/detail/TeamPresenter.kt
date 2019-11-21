package com.dicoding.picodiploma.sejiwaproject.features.team.detail

import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.commons.api.TheSportDBApi
import com.dicoding.picodiploma.sejiwaproject.commons.utils.CoroutineContextProvider
import com.dicoding.picodiploma.sejiwaproject.features.team.model.TeamResponse
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

data class TeamPresenter(
    private var view: TeamView?,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun onDetach() {
        view = null
    }

    fun getDetailTeam(id: String?) {
        view?.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequestAsync(TheSportDBApi.getTeamDetail(id)).await(),
                TeamResponse::class.java
            )

            view?.hideLoading()
            view?.showDetailTeam(data.teams.first())
        }
    }
}