package com.dicoding.picodiploma.sejiwaproject.features.match.detailMatch

import com.dicoding.picodiploma.sejiwaproject.features.match.detailMatch.model.DetailMatch
import com.dicoding.picodiploma.sejiwaproject.features.match.detailMatch.model.LogoTeam

interface DetailMatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchDetail(data: List<DetailMatch>)
    fun showHomeLogo(data: List<LogoTeam>)
    fun showAwayLogo(data: List<LogoTeam>)
}