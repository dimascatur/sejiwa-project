package com.dicoding.picodiploma.sejiwaproject.features.team.model

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("idTeam")
    var idTeam: String? = null,

    @SerializedName("strTeam")
    var teamName: String? = null,

    @SerializedName("strStadiumThumb")
    var teamStadium: String? = null,

    @SerializedName("strTeamBadge")
    var teamLogo: String? = null
)