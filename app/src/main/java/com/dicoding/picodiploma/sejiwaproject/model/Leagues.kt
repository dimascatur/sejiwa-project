package com.dicoding.picodiploma.sejiwaproject.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Leagues(
    @SerializedName( "idLeague")
    var teamId: String? = null,

    @SerializedName("strLeague")
    var teamName: String? = null,

    @SerializedName("strCountry")
    var teamLocation: String? = null,

    @SerializedName("intFormedYear")
    var teamFormed: String? = null,

    @SerializedName("strFanart1")
    var teamPoster: String? = null
) :Parcelable
