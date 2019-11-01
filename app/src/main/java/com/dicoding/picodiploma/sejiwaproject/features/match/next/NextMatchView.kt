package com.dicoding.picodiploma.sejiwaproject.features.match.next

import com.dicoding.picodiploma.sejiwaproject.features.match.next.model.NextMatch

interface NextMatchView{
    fun showLoading()
    fun hideLoading()
    fun showNextMatch(data: List<NextMatch>)}