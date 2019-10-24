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

    @SerializedName("strDescriptionEN")
    var teamDescription: String? = null,

    @SerializedName("intFormedYear")
    var teamFormed: String? = null,

    @SerializedName("strBadge")
    var teamBadge: String? = null,

    @SerializedName("strPoster")
    var teamPoster: String? = null
) :Parcelable
