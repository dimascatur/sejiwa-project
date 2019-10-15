package com.dicoding.picodiploma.sejiwaproject.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League(
    var name: String = "",
    var description: String = "",
    var location: String = "",
    var photo: Int = 0
) : Parcelable