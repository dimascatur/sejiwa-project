package com.dicoding.picodiploma.sejiwaproject

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League(
    var name: String = "",
    var description: String = "",
    var stadium: String = "",
    var coach: String = "",
    var profileCoach: Int = R.drawable.arsenal_coach,
    var logo: Int = R.drawable.arsenal_logo,
    var background: Int = R.drawable.arsenal_background
) : Parcelable