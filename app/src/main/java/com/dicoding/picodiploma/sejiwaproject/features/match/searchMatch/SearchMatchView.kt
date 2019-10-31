package com.dicoding.picodiploma.sejiwaproject.features.match.searchMatch

import com.dicoding.picodiploma.sejiwaproject.features.match.searchMatch.model.SearchMatch

interface SearchMatchView {
    fun showLoading()
    fun hideLoading()
    fun showSearchList(data: List<SearchMatch>)
}