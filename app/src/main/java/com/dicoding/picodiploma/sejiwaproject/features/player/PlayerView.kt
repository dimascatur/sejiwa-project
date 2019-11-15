package com.dicoding.picodiploma.sejiwaproject.features.player

import com.dicoding.picodiploma.sejiwaproject.features.player.model.Player

interface PlayerView {
    fun showLoading()
    fun hideLoading()
    fun showPlayerGrid(data: List<Player>)
}