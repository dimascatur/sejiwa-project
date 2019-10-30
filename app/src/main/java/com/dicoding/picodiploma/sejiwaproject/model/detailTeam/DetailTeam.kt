package com.dicoding.picodiploma.sejiwaproject.model.detailTeam

import com.google.gson.annotations.SerializedName

data class DetailTeam(
    @SerializedName("idTeam")
    var idTeam: String? = null,

    @SerializedName("strTeamBadge")
    var teamLogo: String? = null
)