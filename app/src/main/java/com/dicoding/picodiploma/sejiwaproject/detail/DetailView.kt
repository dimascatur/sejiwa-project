package com.dicoding.picodiploma.sejiwaproject.detail

import com.dicoding.picodiploma.sejiwaproject.model.detailLeague.Leagues

interface DetailView {
    fun showLoading()
    fun hideLoading()
    fun showDetailList(data: List<Leagues>)
}