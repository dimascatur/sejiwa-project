package com.dicoding.picodiploma.sejiwaproject.features.match.standings.model

import com.google.gson.annotations.SerializedName

data class StandingsMatch(
    @SerializedName("name")
    var teamName: String? = null,

    @SerializedName("teamid")
    var teamId: String? = null,

    @SerializedName("played")
    var teamPlayed: String? = null,

    @SerializedName("goalsfor")
    var teamGoal: String? = null,

    @SerializedName("win")
    var teamWin: String? = null,

    @SerializedName("draw")
    var teamDraw: String? = null,

    @SerializedName("loss")
    var teamLose: String? = null,

    @SerializedName("total")
    var teamPoint: String? = null,

    var teamBadge: String? = null
    )