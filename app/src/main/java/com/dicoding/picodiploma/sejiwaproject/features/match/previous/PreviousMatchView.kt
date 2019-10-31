package com.dicoding.picodiploma.sejiwaproject.features.match.previousMatch

import com.dicoding.picodiploma.sejiwaproject.features.match.previousMatch.model.Matchs

interface PreviousMatchView{
    fun showLoading()
    fun hideLoading()
    fun showPreviousMatch(data: List<Matchs>)

}