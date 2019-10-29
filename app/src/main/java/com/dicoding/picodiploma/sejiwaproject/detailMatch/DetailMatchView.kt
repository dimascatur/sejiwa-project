package com.dicoding.picodiploma.sejiwaproject.detailMatch

import com.dicoding.picodiploma.sejiwaproject.model.detailMatch.DetailMatch

interface DetailMatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchDetail(data: List<DetailMatch>)
}