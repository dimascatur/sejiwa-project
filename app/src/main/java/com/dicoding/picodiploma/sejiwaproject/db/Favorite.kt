package com.dicoding.picodiploma.sejiwaproject.db

data class Favorite(val matchId: String?, val teamHome: String?,
                    val teamAway: String?, val dateMatch: String?) {

    companion object{
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val MATCH_ID: String = "MATCH_ID"
        const val TEAM_HOME: String = "TEAM_HOME"
        const val TEAM_AWAY: String = "TEAM_AWAY"
        const val DATE_MATCH: String = "DATE_MATCH"
    }
}