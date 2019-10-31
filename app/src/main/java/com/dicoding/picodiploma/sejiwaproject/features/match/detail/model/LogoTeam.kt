package com.dicoding.picodiploma.sejiwaproject.features.match.detailMatch.model

import com.google.gson.annotations.SerializedName

data class LogoTeam(
    @SerializedName("idTeam")
    var idTeam: String? = null,

    @SerializedName("strTeamBadge")
    var teamLogo: String? = null
)