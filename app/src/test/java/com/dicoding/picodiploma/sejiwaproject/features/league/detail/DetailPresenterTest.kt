package com.dicoding.picodiploma.sejiwaproject.features.league.detail

import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.features.TestContextProvider
import com.dicoding.picodiploma.sejiwaproject.features.league.detail.model.League
import com.dicoding.picodiploma.sejiwaproject.features.league.detail.model.LeagueResponse
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailPresenterTest {
    @Mock
    private lateinit var view: DetailView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: DetailPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun `test get detail league is ready`() {
        val league: MutableList<League> = mutableListOf()
        val response = LeagueResponse(league)
        val idLeague = "4346"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    "",
                    LeagueResponse::class.java)
            ).thenReturn(response)

            presenter.getDetailList(idLeague)

            Mockito.verify(view).showDetailList(league)
        }
    }
}