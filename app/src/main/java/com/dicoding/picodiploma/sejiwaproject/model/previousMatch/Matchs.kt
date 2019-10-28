package com.dicoding.picodiploma.sejiwaproject.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Matchs(
    @SerializedName("idEvent")
    var matchId: String? = null,

    @SerializedName("strEvent")
    var matchTitle: String? = null,

    @SerializedName("strHomeTeam")
    var teamHome: String? = null,

    @SerializedName("strAwayTeam")
    var teamAway: String? = null,

    @SerializedName("intHomeScore")
    var homeScore: String? = null,

    @SerializedName("intAwayScore")
    var awayScore: String? = null
):Parcelable