package com.dicoding.picodiploma.sejiwaproject.features.player

import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.commons.api.TheSportDBApi
import com.dicoding.picodiploma.sejiwaproject.features.player.model.PlayerDetailResponse
import com.dicoding.picodiploma.sejiwaproject.features.player.model.PlayerResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PlayerPresenter(
    private val view: PlayerView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {
    fun getPlayerLogo(idTeam: String) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getPlayerLogo(idTeam)),
                PlayerResponse::class.java
            )
            uiThread {
                view.hideLoading()
                view.showPlayerDetail(data.player)
            }
        }
    }

    fun getPlayerDetail(idPlayer: String) {
        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getPlayerDetail(idPlayer)),
                PlayerDetailResponse::class.java
            )
            uiThread {
                view.hideLoading()
                view.showPlayerDetail(data.players)
            }
        }
    }
}