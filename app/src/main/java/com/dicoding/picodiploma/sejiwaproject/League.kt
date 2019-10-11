package com.dicoding.picodiploma.sejiwaproject

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League(
    var name: String = "",
    var description: String = "",
    var stadium: String = "",
    var coach: String = "",
    var profileCoach: Int,
    var logo: Int = 0,
    var background: Int = 0
) : Parcelable