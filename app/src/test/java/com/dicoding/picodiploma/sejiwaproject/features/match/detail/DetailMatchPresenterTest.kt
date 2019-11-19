package com.dicoding.picodiploma.sejiwaproject.features.match.detail

import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.db.MyDatabaseOpenHelper
import com.dicoding.picodiploma.sejiwaproject.features.TestContextProvider
import com.dicoding.picodiploma.sejiwaproject.features.match.detail.model.DetailMatch
import com.dicoding.picodiploma.sejiwaproject.features.match.detail.model.DetailMatchResponse
import com.dicoding.picodiploma.sejiwaproject.features.team.model.Team
import com.dicoding.picodiploma.sejiwaproject.features.team.model.TeamResponse
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailMatchPresenterTest {
    @Mock
    private lateinit var view: DetailMatchView

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var database: MyDatabaseOpenHelper

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: DetailMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailMatchPresenter(view, apiRepository, gson, database, TestContextProvider())

    }


    @Test
    fun `test get detail match is ready`() {
        val match = DetailMatch(
            "441613", "133632", "133632", "Liverpool vs Swansea",
            "Liverpool", "Swansea", "4", "1",
            "69':Own  Jonjo Shelvey;61': Adam Lallana;51': Adam Lallana;33': Alberto Moreno;",
            "52': Gylfi Sigurdsson;", "", "",
            "49': Martin Skrtel;",
            "", "2014-12-29"
        )

        val matches: MutableList<DetailMatch> = mutableListOf(match)
        val response = DetailMatchResponse(matches)

        val teamHome = Team(
            idTeam = "133632",
            teamBadge = "https://www.thesportsdb.com/images/media/team/badge/a1af2i1557005128.png"
        )

        val teamsHome: MutableList<Team> = mutableListOf(teamHome)
        val teamHomeResponse = TeamResponse(teamsHome)

        val teamAway = Team(
            idTeam = "133632",
            teamBadge = "https://www.thesportsdb.com/images/media/team/badge/a1af2i1557005128.png"
        )

        val teamsAway: MutableList<Team> = mutableListOf(teamAway)
        val teamAwayResponse = TeamResponse(teamsAway)

        val idEvent = "441613"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await())
                .thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    DetailMatchResponse::class.java
                )
            ).thenReturn(response)

            Mockito.`when`(
                gson.fromJson(
                    "",
                    TeamResponse::class.java
                )
            ).thenReturn(teamHomeResponse)

            Mockito.`when`(
                gson.fromJson(
                    "",
                    TeamResponse::class.java
                )
            ).thenReturn(teamAwayResponse)

            presenter.getDetailMatch(idEvent)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).matchReady(match)
            Mockito.verify(view).hideLoading()
        }
    }
}