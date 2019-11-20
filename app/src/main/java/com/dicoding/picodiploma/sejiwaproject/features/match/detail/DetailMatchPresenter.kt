package com.dicoding.picodiploma.sejiwaproject.features.match.detail

import android.database.sqlite.SQLiteConstraintException
import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.commons.api.TheSportDBApi
import com.dicoding.picodiploma.sejiwaproject.commons.utils.CoroutineContextProvider
import com.dicoding.picodiploma.sejiwaproject.commons.utils.EspressoIdlingResource
import com.dicoding.picodiploma.sejiwaproject.db.FavoriteMatch
import com.dicoding.picodiploma.sejiwaproject.db.MyDatabaseOpenHelper
import com.dicoding.picodiploma.sejiwaproject.features.match.detail.model.DetailMatch
import com.dicoding.picodiploma.sejiwaproject.features.match.detail.model.DetailMatchResponse
import com.dicoding.picodiploma.sejiwaproject.features.team.model.TeamResponse
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DetailMatchPresenter(
    private var view: DetailMatchView?,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val database: MyDatabaseOpenHelper,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun onDetach() {
        view = null
    }

    fun getDetailMatch(id: String?) {
        view?.showLoading()
        EspressoIdlingResource.increment()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequestAsync(TheSportDBApi.getMatchDetails(id)).await(),
                DetailMatchResponse::class.java
            )

            data.events.map {
                val homeResponse = gson.fromJson(
                    apiRepository
                        .doRequestAsync(TheSportDBApi.getTeamDetail(it.homeId)).await(),
                    TeamResponse::class.java
                )

                val awayResponse = gson.fromJson(
                    apiRepository
                        .doRequestAsync(TheSportDBApi.getTeamDetail(it.awayId)).await(),
                    TeamResponse::class.java
                )

                val result = it.copy()
                result.awayId = awayResponse.teams.first().idTeam
                result.homeId = homeResponse.teams.first().idTeam
                result.badgeHome = homeResponse.teams.first().teamLogo
                result.badgeAway = awayResponse.teams.first().teamLogo
                result.homeStadium = homeResponse.teams.first().teamStadium

                EspressoIdlingResource.decrement()
                view?.hideLoading()
                view?.matchReady(result)
            }
        }
    }

    fun favoriteState(matchDetail: String) {
        database.use {
            val result = select(FavoriteMatch.TABLE_FAVORITE)
                .whereArgs(
                    "(MATCH_ID = {id})",
                    "id" to matchDetail
                )
            val favorite = result.parseList(classParser<FavoriteMatch>())
            if (favorite.isNotEmpty()) view?.favoriteState(true)
        }
    }

    fun addToFavorite(match: DetailMatch) {
        try {
            database.use {
                insert(
                    FavoriteMatch.TABLE_FAVORITE,
                    FavoriteMatch.MATCH_ID to match.matchId,
                    FavoriteMatch.TEAM_HOME to match.teamHome,
                    FavoriteMatch.TEAM_AWAY to match.teamAway,
                    FavoriteMatch.DATE_MATCH to match.dateMatch,
                    FavoriteMatch.BADGE_HOME to match.badgeHome,
                    FavoriteMatch.BADGE_AWAY to match.badgeAway
                )
            }
        } catch (e: SQLiteConstraintException) {

        }
    }

    fun removeFromFavorite(matchDetail: String) {
        try {
            database.use {
                delete(
                    FavoriteMatch.TABLE_FAVORITE, "(MATCH_ID = {id})",
                    "id" to matchDetail
                )
            }
        } catch (e: SQLiteConstraintException) {
        }
    }
}