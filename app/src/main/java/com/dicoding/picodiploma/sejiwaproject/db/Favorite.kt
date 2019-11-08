package com.dicoding.picodiploma.sejiwaproject.features.favorite

data class Favorite(val matchId: String?, val teamHome: String?, val homeId: String?,
                    val awayId: String?, val teamAway: String?, val dateMatch: String?,
                    val badgeHome: String?, val badgeAway: String?) {

    companion object{
        const val
    }
}