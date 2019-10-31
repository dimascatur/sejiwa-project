package com.dicoding.picodiploma.sejiwaproject.features.match.nextMatch

import com.dicoding.picodiploma.sejiwaproject.features.match.nextMatch.model.NextMatch

interface NextMatchView{
    fun showLoading()
    fun hideLoading()
    fun showNextMatch(data: List<NextMatch>)}