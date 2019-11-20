package com.dicoding.picodiploma.sejiwaproject.db

data class FavoriteMatch(val matchId: String?, val teamHome: String?,
                         val teamAway: String?, val dateMatch: String?,
                         val badgeHome: String?, val badgeAway: String?) {

    companion object{
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val MATCH_ID: String = "MATCH_ID"
        const val TEAM_HOME: String = "TEAM_HOME"
        const val TEAM_AWAY: String = "TEAM_AWAY"
        const val DATE_MATCH: String = "DATE_MATCH"
        const val BADGE_HOME: String = "BADGE_HOME"
        const val BADGE_AWAY: String = "BADGE_AWAY"
    }
}