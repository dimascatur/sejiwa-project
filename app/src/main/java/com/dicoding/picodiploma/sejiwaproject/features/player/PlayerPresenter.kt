package com.dicoding.picodiploma.sejiwaproject.features.player

import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.commons.api.TheSportDBApi
import com.dicoding.picodiploma.sejiwaproject.commons.utils.CoroutineContextProvider
import com.dicoding.picodiploma.sejiwaproject.features.player.model.PlayerDetailResponse
import com.dicoding.picodiploma.sejiwaproject.features.player.model.PlayerResponse
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PlayerPresenter(
    private var view: PlayerView?,
    private val apiRepository: ApiRepository,
    private val gson: Gson, private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getPlayerLogo(idTeam: String) {
        view?.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequestAsync(TheSportDBApi.getPlayerLogo(idTeam)).await(),
                PlayerResponse::class.java
            )

                view?.hideLoading()
                view?.showPlayerDetail(data.player)
        }
    }

    fun getPlayerDetail(idPlayer: String) {
        view?.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequestAsync(TheSportDBApi.getPlayerDetail(idPlayer)).await(),
                PlayerDetailResponse::class.java
            )
                view?.hideLoading()
                view?.showPlayerDetail(data.players)
            }
        }

    fun onDetach() {
        view = null
    }
}