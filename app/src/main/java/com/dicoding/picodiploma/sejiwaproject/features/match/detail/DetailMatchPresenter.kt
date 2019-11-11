package com.dicoding.picodiploma.sejiwaproject.features.match.detail

import android.database.sqlite.SQLiteConstraintException
import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.commons.api.TheSportDBApi
import com.dicoding.picodiploma.sejiwaproject.db.Favorite
import com.dicoding.picodiploma.sejiwaproject.db.MyDatabaseOpenHelper
import com.dicoding.picodiploma.sejiwaproject.features.match.detail.model.DetailMatch
import com.dicoding.picodiploma.sejiwaproject.features.match.detail.model.DetailMatchResponse
import com.dicoding.picodiploma.sejiwaproject.features.team.model.TeamResponse
import com.google.gson.Gson
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailMatchPresenter(
    private var view: DetailMatchView?,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val database: MyDatabaseOpenHelper
) {

    fun onDetach() {
        view = null
    }

    fun getDetailMatch(id: String?) {
        view?.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository
                    .doRequest(TheSportDBApi.getMatchDetails(id)),
                DetailMatchResponse::class.java
            )

            data.events.map {
                val homeResponse = gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getTeamDetail(it.homeId)),
                    TeamResponse::class.java
                )

                val awayResponse = gson.fromJson(
                    apiRepository
                        .doRequest(TheSportDBApi.getTeamDetail(it.awayId)),
                    TeamResponse::class.java
                )

                val result = it.copy()
                result.awayId = awayResponse.teams.first().idTeam
                result.homeId = homeResponse.teams.first().idTeam
                result.badgeHome = homeResponse.teams.first().teamLogo
                result.badgeAway = awayResponse.teams.first().teamLogo

                uiThread {
                    view?.hideLoading()
                    view?.matchReady(result)
                }
            }
        }
    }

    fun favoriteState(matchDetail: String) {
           database.use {
               val result = select(Favorite.TABLE_FAVORITE)
                   .whereArgs(
                       "(MATCH_ID = {id})",
                       "id" to matchDetail
                   )
               val favorite = result.parseList(classParser<Favorite>())
               if (favorite.isNotEmpty()) view?.favoriteState(true)
           }
    }

    fun addToFavorite(match: DetailMatch) {
        try {
            database.use {
                insert(
                    Favorite.TABLE_FAVORITE,
                    Favorite.MATCH_ID to match.matchId,
                    Favorite.TEAM_HOME to match.teamHome,
                    Favorite.TEAM_AWAY to match.teamAway,
                    Favorite.DATE_MATCH to match.dateMatch,
                    Favorite.BADGE_HOME to match.badgeHome,
                    Favorite.BADGE_AWAY to match.badgeAway
                )
            }
        } catch (e: SQLiteConstraintException) {

        }
    }

    fun removeFromFavorite(matchDetail: String) {
        try {
            database.use {
                delete(
                    Favorite.TABLE_FAVORITE, "(MATCH_ID = {id})",
                    "id" to matchDetail
                )
            }
        } catch (e: SQLiteConstraintException) {
        }
    }



}