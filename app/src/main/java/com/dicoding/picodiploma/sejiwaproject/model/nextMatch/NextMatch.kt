package com.dicoding.picodiploma.sejiwaproject.model.nextMatch

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NextMatch(
    @SerializedName("idEvent")
    var matchId: String? = null,

    @SerializedName("strHomeTeam")
    var teamHome: String? = null,

    @SerializedName("idHomeTeam")
    var homeId: String? = null,

    @SerializedName("idAwayTeam")
    var awayId: String? = null,

    @SerializedName("strAwayTeam")
    var teamAway: String? = null,

    @SerializedName("dateEvent")
    var dateMatch: String? = null
) :Parcelable