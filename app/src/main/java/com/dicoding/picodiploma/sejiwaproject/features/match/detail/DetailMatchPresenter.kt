package com.dicoding.picodiploma.sejiwaproject.features.match.detail

import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.commons.api.TheSportDBApi
import com.dicoding.picodiploma.sejiwaproject.features.match.detail.model.DetailMatchResponse
import com.dicoding.picodiploma.sejiwaproject.features.team.model.TeamResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailMatchPresenter (private var view: DetailMatchView?,
                            private val apiRepository: ApiRepository,
                            private val gson: Gson) {

    fun onDetach(){
        view = null
    }

    fun getDetailMatch(id: String?) {
        view?.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getMatchDetails(id)),
                DetailMatchResponse::class.java
            )
            uiThread {
                view?.hideLoading()
                view?.showMatchDetail(data.events)
            }
        }
    }
    fun getTeamLogo(id: String?, isHome: Boolean){
            view?.showLoading()
            doAsync {
                val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeamDetail(id)),
                    TeamResponse::class.java
                )
                uiThread {
                    if (isHome){
                        view?.hideLoading()
                        view?.showHomeLogo(data.teams)
                    } else {
                        view?.hideLoading()
                        view?.showAwayLogo(data.teams)
                    }

                }
            }
    }
}