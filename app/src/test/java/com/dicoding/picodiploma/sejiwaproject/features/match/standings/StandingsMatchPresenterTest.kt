package com.dicoding.picodiploma.sejiwaproject.features.match.standings

import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.features.TestContextProvider
import com.dicoding.picodiploma.sejiwaproject.features.match.standings.model.StandingsMatch
import com.dicoding.picodiploma.sejiwaproject.features.match.standings.model.StandingsMatchResponse
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

class StandingsMatchPresenterTest {

    @Mock
    private lateinit var view: StandingsMatchView

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: StandingsMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = StandingsMatchPresenter(view, apiRepository, gson, TestContextProvider())

    }

    @Test
    fun `test get standings match is ready`() {
        val match = StandingsMatch(
            "Man City", "133613", "38", "151", "32",
            "2", "4", "98"
        )

        val matches: MutableList<StandingsMatch> = mutableListOf(match)
        val response = StandingsMatchResponse(matches)

        val team = Team(
            idTeam = "133613",
            teamBadge = "https://www.thesportsdb.com/images/media/team/badge/a1af2i1557005128.png"
        )

        val teamsLogo: MutableList<Team> = mutableListOf(team)
        val teamLogoResponse = TeamResponse(teamsLogo)

        val idLeague = "4328"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await())
                .thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    StandingsMatchResponse::class.java
                )
            ).thenReturn(response)

            Mockito.`when`(
                gson.fromJson(
                    "",
                    TeamResponse::class.java
                )
            ).thenReturn(teamLogoResponse)

            presenter.getStandingsLeague(idLeague)

            Mockito.verify(view).showLoading()
            Mockito.verify(view, Mockito.times(matches.size)).showStandingsList(match)
            Mockito.verify(view).hideLoading()

        }
    }
}