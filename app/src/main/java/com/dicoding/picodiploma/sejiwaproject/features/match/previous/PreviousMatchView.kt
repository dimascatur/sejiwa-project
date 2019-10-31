package com.dicoding.picodiploma.sejiwaproject.features.match.previous

import com.dicoding.picodiploma.sejiwaproject.features.match.previous.model.Matchs

interface PreviousMatchView{
    fun showLoading()
    fun hideLoading()
    fun showPreviousMatch(data: List<Matchs>)

}