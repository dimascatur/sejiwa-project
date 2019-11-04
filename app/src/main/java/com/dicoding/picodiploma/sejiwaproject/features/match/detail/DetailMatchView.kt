package com.dicoding.picodiploma.sejiwaproject.features.match.detail

import com.dicoding.picodiploma.sejiwaproject.features.match.detail.model.DetailMatch
import com.dicoding.picodiploma.sejiwaproject.features.team.model.Team

interface DetailMatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchDetail(data: List<DetailMatch>)
    fun showHomeLogo(data: List<Team>)
    fun showAwayLogo(data: List<Team>)
}