package com.dicoding.picodiploma.sejiwaproject.features.player.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Player(
    @SerializedName("idTeam")
    var idTeam: String? = null,

    @SerializedName("idPlayer")
    var playerId: String? = null,

    @SerializedName("strCutout")
    var playerImg: String? = null,

    @SerializedName("strPlayer")
    var playerName: String? = null,

    @SerializedName("strFanart1")
    var playerBackground: String? = null,

    @SerializedName("strDescriptionEN")
    var playerDescription: String? = null,

    @SerializedName("dateBorn")
    var playerBorn: String? = null,

    @SerializedName("strBirthLocation")
    var bornLocation: String? = null,

    @SerializedName("strPosition")
    var playerPosition: String? = null,

    @SerializedName("strNationality")
    var playerNationality: String? = null
): Parcelable