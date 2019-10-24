package com.dicoding.picodiploma.sejiwaproject.detail

import com.dicoding.picodiploma.sejiwaproject.model.Leagues

interface DetailView {
    fun showLoading()
    fun hideLoading()
    fun showDetailList(data: List<Leagues>)
}