package com.dicoding.picodiploma.sejiwaproject.features.match.next

import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.features.TestContextProvider
import com.dicoding.picodiploma.sejiwaproject.features.match.next.model.NextMatch
import com.dicoding.picodiploma.sejiwaproject.features.match.next.model.NextMatchResponse
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

class NextMatchPresenterTest{
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
    fun testGetNextMatch() {
        var matches= NextMatch(
            "602249", "Crystal Palace",
            "133632", "133602", "Liverpool",
            "2019-11-23", "crystal logo", "liverpool logo"
        )
        val match: MutableList<NextMatch> = mutableListOf(matches)
        val response = NextMatchResponse(match)
        val idLeague = "4328"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString())).thenReturn(apiResponse)

            Mockito.`when`(apiResponse.await()).thenReturn("")

            Mockito.`when`(
                gson.fromJson(
                    "",
                    NextMatchResponse::class.java)
            ).thenReturn(response)

            presenter.getNextMatch(idLeague)

            Mockito.verify(view).showLoading()
            Mockito.verify(view, times(1)).matchReady(matches)
            Mockito.verify(view).hideLoading()
        }
    }
}