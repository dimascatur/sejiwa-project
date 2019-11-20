package com.dicoding.picodiploma.sejiwaproject.features.match.previous

import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.features.TestContextProvider
import com.dicoding.picodiploma.sejiwaproject.features.match.previous.model.PreviousMatch
import com.dicoding.picodiploma.sejiwaproject.features.match.previous.model.PreviousMatchResponse
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

class PreviousMatchPresenterTest {
    @Mock
    private lateinit var view: PreviousMatchView

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: PreviousMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = PreviousMatchPresenter(view, apiRepository, gson, TestContextProvider())

    }

    @Test
    fun `test get match is ready`() {
        val match = PreviousMatch(
            "602247", "Man United vs Brighton", "Man United",
            "Brighton", "133632", "133632", "3", "1",
            "2019-11-10"
        )

        val matches: MutableList<PreviousMatch> = mutableListOf(match)
        val response = PreviousMatchResponse(matches)

        val team = Team(
            idTeam = "133632",
            teamBadge = "https://www.thesportsdb.com/images/media/team/badge/a1af2i1557005128.png"
        )

        val teamsHome: MutableList<Team> = mutableListOf(team)
        val teamHomeResponse = TeamResponse(teamsHome)

        val teamsAway: MutableList<Team> = mutableListOf(team)
        val teamAwayResponse = TeamResponse(teamsAway)

        val idLeague = "4328"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await())
                .thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    PreviousMatchResponse::class.java
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

            presenter.getPreviousMatch(idLeague)

            Mockito.verify(view).showLoading()
            Mockito.verify(view, Mockito.times(matches.size)).matchReady(match)
            Mockito.verify(view).hideLoading()
        }
    }
}