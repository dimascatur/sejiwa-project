package com.dicoding.picodiploma.sejiwaproject.commons.api

import com.dicoding.picodiploma.sejiwaproject.BuildConfig

object TheSportDBApi {
    fun getDetails(idLeague: String?): String {
        return StringBuilder()
            .append(BuildConfig.BASE_URL)
            .append("api").append("/")
            .append("v1").append("/")
            .append("json").append("/")
            .append(BuildConfig.TSDB_API_KEY).append("/")
            .append("lookupleague.php").append("?")
            .append("id").append("=").append(idLeague)
            .toString()
    }

    fun getPreviousMatches(idEvent: String?): String {
        return StringBuilder()
            .append(BuildConfig.BASE_URL)
            .append("api").append("/")
            .append("v1").append("/")
            .append("json").append("/")
            .append(BuildConfig.TSDB_API_KEY).append("/")
            .append("eventspastleague.php").append("?")
            .append("id").append("=").append(idEvent)
            .toString()
    }

    fun getNextMatches(idEvent: String?): String {
        return StringBuilder()
            .append(BuildConfig.BASE_URL)
            .append("api").append("/")
            .append("v1").append("/")
            .append("json").append("/")
            .append(BuildConfig.TSDB_API_KEY).append("/")
            .append("eventsnextleague.php").append("?")
            .append("id").append("=").append(idEvent)
            .toString()
    }

    fun getMatchDetails(idEvent: String?): String {
        return StringBuilder()
            .append(BuildConfig.BASE_URL)
            .append("api").append("/")
            .append("v1").append("/")
            .append("json").append("/")
            .append(BuildConfig.TSDB_API_KEY).append("/")
            .append("lookupevent.php").append("?")
            .append("id").append("=").append(idEvent)
            .toString()
    }

    fun getTeamDetail(idTeam: String?): String {
        return StringBuilder()
            .append(BuildConfig.BASE_URL)
            .append("api").append("/")
            .append("v1").append("/")
            .append("json").append("/")
            .append(BuildConfig.TSDB_API_KEY).append("/")
            .append("lookupteam.php").append("?")
            .append("id").append("=").append(idTeam)
            .toString()
    }

    fun getSearchMatches(event: String?): String {
        return StringBuilder()
            .append(BuildConfig.BASE_URL)
            .append("api").append("/")
            .append("v1").append("/")
            .append("json").append("/")
            .append(BuildConfig.TSDB_API_KEY).append("/")
            .append("searchevents.php").append("?")
            .append("e").append("=").append(event)
            .toString()
    }

    fun getPlayerLogo(idTeam: String?): String {
        return StringBuilder()
            .append(BuildConfig.BASE_URL)
            .append("api").append("/")
            .append("v1").append("/")
            .append("json").append("/")
            .append(BuildConfig.TSDB_API_KEY).append("/")
            .append("lookup_all_players.php").append("?")
            .append("id").append("=").append(idTeam)
            .toString()
    }

    fun getPlayerDetail(idPlayer: String?): String {
        return StringBuilder()
            .append(BuildConfig.BASE_URL)
            .append("api").append("/")
            .append("v1").append("/")
            .append("json").append("/")
            .append(BuildConfig.TSDB_API_KEY).append("/")
            .append("lookupplayer.php").append("?")
            .append("id").append("=").append(idPlayer)
            .toString()
    }

    fun getStandingsMatch(idLeague: String?): String {
        return StringBuilder()
            .append(BuildConfig.BASE_URL)
            .append("api").append("/")
            .append("v1").append("/")
            .append("json").append("/")
            .append(BuildConfig.TSDB_API_KEY).append("/")
            .append("lookuptable.php").append("?")
            .append("l").append("=").append(idLeague)
            .toString()

    }
}