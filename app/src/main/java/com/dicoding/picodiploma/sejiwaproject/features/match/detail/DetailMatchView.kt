package com.dicoding.picodiploma.sejiwaproject.features.match.detail

import com.dicoding.picodiploma.sejiwaproject.features.match.detail.model.DetailMatch

interface DetailMatchView {
    fun showLoading()
    fun hideLoading()
    fun matchReady(detailMatch: DetailMatch)
    fun favoriteState(isFavorite: Boolean)
}