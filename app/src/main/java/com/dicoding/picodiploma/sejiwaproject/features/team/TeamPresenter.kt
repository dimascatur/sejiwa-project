package com.dicoding.picodiploma.sejiwaproject.features.team

import android.database.sqlite.SQLiteConstraintException
import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.commons.api.TheSportDBApi
import com.dicoding.picodiploma.sejiwaproject.commons.utils.CoroutineContextProvider
import com.dicoding.picodiploma.sejiwaproject.db.FavoriteTeam
import com.dicoding.picodiploma.sejiwaproject.db.MyDatabaseOpenHelper
import com.dicoding.picodiploma.sejiwaproject.features.team.model.Team
import com.dicoding.picodiploma.sejiwaproject.features.team.model.TeamResponse
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class TeamPresenter(
    private val view: TeamView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val database: MyDatabaseOpenHelper,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getDetailTeam(id: String?) {
        view.showLoading()
        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                apiRepository
                    .doRequestAsync(TheSportDBApi.getTeamDetail(id)).await(),
                TeamResponse::class.java
            )

            view.hideLoading()
            view.showDetailTeam(data.teams.first())
        }
    }

    fun favoriteState(teamId: String) {
        database.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE)
                .whereArgs(
                    "(TEAM_ID = {id})",
                    "id" to teamId
                )
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (favorite.isNotEmpty()) view.favoriteState(true)
        }
    }

    fun addToFavorite(team: Team) {
        try {
            database.use {
                insert(
                    FavoriteTeam.TABLE_FAVORITE,
                    FavoriteTeam.TEAM_ID to team.idTeam,
                    FavoriteTeam.TEAM_NAME to team.teamName,
                    FavoriteTeam.TEAM_LOGO to team.teamLogo
                )
            }
        } catch (e: SQLiteConstraintException) {
        }
    }

    fun removeFromFavorite(teamId: String) {
        try {
            database.use {
                delete(
                    FavoriteTeam.TABLE_FAVORITE, "(TEAM_ID = {id})",
                    "id" to teamId
                )
            }
        } catch (e: SQLiteConstraintException) {
        }
    }
}
