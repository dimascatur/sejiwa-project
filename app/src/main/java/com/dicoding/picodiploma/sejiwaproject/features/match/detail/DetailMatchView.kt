package com.dicoding.picodiploma.sejiwaproject.features.match.detail

import com.dicoding.picodiploma.sejiwaproject.features.match.detail.model.DetailMatch
import com.dicoding.picodiploma.sejiwaproject.features.match.detail.model.LogoTeam

interface DetailMatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchDetail(data: List<DetailMatch>)
    fun showHomeLogo(data: List<LogoTeam>)
    fun showAwayLogo(data: List<LogoTeam>)
}