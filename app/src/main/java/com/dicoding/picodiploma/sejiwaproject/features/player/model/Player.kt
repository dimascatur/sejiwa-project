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
    var playerName: String? = null
): Parcelable