package com.dicoding.picodiploma.sejiwaproject.features.team

import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.commons.api.TheSportDBApi
import com.dicoding.picodiploma.sejiwaproject.commons.utils.CoroutineContextProvider
import com.dicoding.picodiploma.sejiwaproject.features.team.model.TeamResponse
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamPresenter(private val view: TeamView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getDetailTeam(id: String?){
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(apiRepository
                .doRequestAsync(TheSportDBApi.getTeamDetail(id)).await(),
                TeamResponse::class.java
            )

                view.showDetailTeam(data.teams.first())
            }
        }
    }
