package com.dicoding.picodiploma.sejiwaproject.features.league.list

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class League(
    val id: String?,
    val name: String? ,
    val description: String? ,
    val location: String? ,
    val photo: Int?
) : Parcelable