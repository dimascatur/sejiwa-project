package com.dicoding.picodiploma.sejiwaproject.features.match.search

import com.dicoding.picodiploma.sejiwaproject.features.match.search.model.SearchMatch

interface SearchMatchView {
    fun showLoading()
    fun hideLoading()
    fun showSearchList(data: List<SearchMatch>)
}