package com.dicoding.picodiploma.sejiwaproject.features.match.next

import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.features.TestContextProvider
import com.dicoding.picodiploma.sejiwaproject.features.match.next.model.NextMatch
import com.dicoding.picodiploma.sejiwaproject.features.match.next.model.NextMatchResponse
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
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations

class NextMatchPresenterTest {
    @Mock
    private lateinit var view: NextMatchView

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: NextMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = NextMatchPresenter(view, apiRepository, gson, TestContextProvider())

    }

    @Test
    fun `test get match is ready`() {
        val match = NextMatch(
            "602249", "Crystal Palace",
            "133632", "133632", "Liverpool",
            "2019-11-23"
        )

        val matches: MutableList<NextMatch> = mutableListOf(match)
        val response = NextMatchResponse(matches)

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

        val idLeague = "4328"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await())
                .thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    NextMatchResponse::class.java
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

            presenter.getNextMatch(idLeague)

            Mockito.verify(view).showLoading()
            Mockito.verify(view, times(matches.size)).matchReady(match)
            Mockito.verify(view).hideLoading()
        }
    }
}