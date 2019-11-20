package com.dicoding.picodiploma.sejiwaproject.features.team

import com.dicoding.picodiploma.sejiwaproject.features.team.model.Team

interface TeamView {
    fun showDetailTeam(data: Team)
    fun favoriteState(isFavorite: Boolean)
}