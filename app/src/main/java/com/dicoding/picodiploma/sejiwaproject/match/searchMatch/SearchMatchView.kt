package com.dicoding.picodiploma.sejiwaproject.match.searchMatch

import com.dicoding.picodiploma.sejiwaproject.model.searchMatch.SearchMatch

interface SearchMatchView {
    fun showLoading()
    fun hideLoading()
    fun showSearchList(data: List<SearchMatch>)
}