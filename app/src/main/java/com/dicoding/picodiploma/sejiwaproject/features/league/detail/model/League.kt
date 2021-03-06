package com.dicoding.picodiploma.sejiwaproject.features.league.detail.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League(
    @SerializedName( "idLeague")
    var teamId: String? = null,

    @SerializedName("strLeague")
    var teamName: String? = null,

    @SerializedName("strCountry")
    var teamLocation: String? = null,

    @SerializedName("intFormedYear")
    var teamFormed: String? = null,

    @SerializedName("strFanart1")
    var teamPoster: String? = null,

    @SerializedName("strBadge")
    var teamLogo: String? = null
) :Parcelable
