package com.dicoding.picodiploma.sejiwaproject.features.match.standings

import com.dicoding.picodiploma.sejiwaproject.features.match.standings.model.StandingsMatch

interface StandingsMatchView {
    fun showLoading()
    fun hideLoading()
    fun showStandingsList(data: StandingsMatch)
}