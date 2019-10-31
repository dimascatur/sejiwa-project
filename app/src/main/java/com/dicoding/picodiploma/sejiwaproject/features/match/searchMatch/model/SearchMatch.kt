package com.dicoding.picodiploma.sejiwaproject.features.match.searchMatch.model

import com.google.gson.annotations.SerializedName

data class SearchMatch(
    @SerializedName("idEvent")
    var matchId: String? = null,

    @SerializedName("dateEvent")
    var dateMatch: String? = null,

    @SerializedName("strHomeTeam")
    var teamHome: String? = null,

    @SerializedName("strAwayTeam")
    var teamAway: String? = null
    )