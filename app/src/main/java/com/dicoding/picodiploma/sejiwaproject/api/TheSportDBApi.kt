package com.dicoding.picodiploma.sejiwaproject.api

import android.net.Uri
import com.dicoding.picodiploma.sejiwaproject.BuildConfig

object TheSportDBApi {
    fun getDetails(idleague: String?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("lookupleague.php")
            .appendQueryParameter("id", idleague)
            .build()
            .toString()
    }

    fun getPreviousMatchs(idEvent: String?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("eventspastleague.php")
            .appendQueryParameter("id", idEvent)
            .build()
            .toString()
    }

    fun getNextMatchs(idEvent: String?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("eventsnextleague.php")
            .appendQueryParameter("id", idEvent)
            .build()
            .toString()
    }

}