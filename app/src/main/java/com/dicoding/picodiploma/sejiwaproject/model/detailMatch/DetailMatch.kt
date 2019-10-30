package com.dicoding.picodiploma.sejiwaproject.model.detailMatch

import com.google.gson.annotations.SerializedName

data class DetailMatch(
    @SerializedName("idEvent")
    var matchId: String? = null,

    @SerializedName("idHomeTeam")
    var homeId: String? = null,

    @SerializedName("idAwayTeam")
    var awayId: String? = null,

    @SerializedName("strEvent")
    var matchTitle: String? = null,

    @SerializedName("strHomeTeam")
    var teamHome: String? = null,

    @SerializedName("strAwayTeam")
    var teamAway: String? = null,

    @SerializedName("intHomeScore")
    var homeScore: String? = null,

    @SerializedName("intAwayScore")
    var awayScore: String? = null,

    @SerializedName("strHomeGoalDetails")
    var homeGoal: String? = null,

    @SerializedName("strAwayGoalDetails")
    var awayGoal: String? = null,

    @SerializedName("strHomeRedCards")
    var homeRedCard: String? = null,

    @SerializedName("strAwayRedCards")
    var awayRedCard: String? = null,

    @SerializedName("strHomeYellowCards")
    var homeYellowCard: String? = null,

    @SerializedName("strAwayYellowCards")
    var awayYellowCard: String? = null
)