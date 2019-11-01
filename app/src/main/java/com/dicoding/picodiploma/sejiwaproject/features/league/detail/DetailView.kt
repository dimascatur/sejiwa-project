package com.dicoding.picodiploma.sejiwaproject.features.league.detail

import com.dicoding.picodiploma.sejiwaproject.features.league.detail.model.League

interface DetailView {
    fun showLoading()
    fun hideLoading()
    fun showDetailList(data: List<League>)
}