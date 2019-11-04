package com.dicoding.picodiploma.sejiwaproject.features.team

import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.commons.api.TheSportDBApi
import com.dicoding.picodiploma.sejiwaproject.features.team.model.TeamResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailTeamPresenter(private val view: DetailTeamView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson) {

    fun getDetailTeam(id: String?){
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getTeamDetail(id)),
                TeamResponse::class.java
            )
            uiThread {
                view.showDetailTeam(data.teams.first())
            }
        }
    }
}