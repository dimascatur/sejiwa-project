package com.dicoding.picodiploma.sejiwaproject.detailMatch

import com.dicoding.picodiploma.sejiwaproject.model.detailMatch.DetailMatch
import com.dicoding.picodiploma.sejiwaproject.model.detailTeam.DetailTeam

interface DetailMatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchDetail(data: List<DetailMatch>)
    fun showHomeLogo(data: List<DetailTeam>)
    fun showAwayLogo(data: List<DetailTeam>)
}