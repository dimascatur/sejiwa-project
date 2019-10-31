package com.dicoding.picodiploma.sejiwaproject.features.match.detailMatch

import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.commons.api.TheSportDBApi
import com.dicoding.picodiploma.sejiwaproject.features.match.detailMatch.model.DetailMatchResponse
import com.dicoding.picodiploma.sejiwaproject.features.match.detailMatch.model.LogoTeamResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailMatchPresenter (private val view: DetailMatchView,
                            private val apiRepository: ApiRepository,
                            private val gson: Gson) {
    fun getDetailMatch(id: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                .doRequest(TheSportDBApi.getMatchDetail(id)),
                DetailMatchResponse::class.java
            )
            uiThread {
                view.hideLoading()
                view.showMatchDetail(data.events)
            }
        }
    }
    fun getTeamLogo(id: String?, isHome: Boolean){
            view.showLoading()
            doAsync {
                val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeamDetail(id)),
                    LogoTeamResponse::class.java
                )
                uiThread {
                    if (isHome){
                        view.hideLoading()
                        view.showHomeLogo(data.teams)
                    } else {
                        view.hideLoading()
                        view.showAwayLogo(data.teams)
                    }

                }
            }
    }
}