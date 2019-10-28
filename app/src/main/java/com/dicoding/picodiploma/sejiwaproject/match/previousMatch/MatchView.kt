package com.dicoding.picodiploma.sejiwaproject.match

import com.dicoding.picodiploma.sejiwaproject.model.previousMatch.Matchs

interface MatchView{
    fun showLoading()
    fun hideLoading()
    fun showDetailList(data: List<Matchs>)

}