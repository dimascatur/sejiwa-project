package com.dicoding.picodiploma.sejiwaproject.features.team.detail

import com.dicoding.picodiploma.sejiwaproject.features.team.model.Team

interface TeamView {
    fun showLoading()
    fun hideLoading()
    fun showDetailTeam(data: Team)
}