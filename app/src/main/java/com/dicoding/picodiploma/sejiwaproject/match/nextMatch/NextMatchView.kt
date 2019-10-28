package com.dicoding.picodiploma.sejiwaproject.match.nextMatch

import com.dicoding.picodiploma.sejiwaproject.model.nextMatch.NextMatch

interface NextMatchView{
    fun showLoading()
    fun hideLoading()
    fun showNextMatch(data: List<NextMatch>)}