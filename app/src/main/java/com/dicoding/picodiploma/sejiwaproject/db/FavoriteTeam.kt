package com.dicoding.picodiploma.sejiwaproject.db

data class FavoriteTeam(val idTeam: String?, val teamName: String?,
                        val teamBadge: String?) {

    companion object{
        const val TABLE_FAVORITE: String = "TABLE_TEAM"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val TEAM_LOGO: String = "TEAM_LOGO"
    }
}