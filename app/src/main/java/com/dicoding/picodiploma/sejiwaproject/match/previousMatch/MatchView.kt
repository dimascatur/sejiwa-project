package com.dicoding.picodiploma.sejiwaproject.match.previousMatch

import com.dicoding.picodiploma.sejiwaproject.model.previousMatch.Matchs

interface MatchView{
    fun showLoading()
    fun hideLoading()
    fun showPreviousMatch(data: List<Matchs>)

}