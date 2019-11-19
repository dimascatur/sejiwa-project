package com.dicoding.picodiploma.sejiwaproject.features.match.search

import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.features.TestContextProvider
import com.dicoding.picodiploma.sejiwaproject.features.match.search.model.SearchMatch
import com.dicoding.picodiploma.sejiwaproject.features.match.search.model.SearchMatchResponse
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class SearchMatchPresenterTest{

    @Mock
    private lateinit var view: SearchMatchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: SearchMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SearchMatchPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun `test get search match is ready`() {
        val event: MutableList<SearchMatch> = mutableListOf()
        val response = SearchMatchResponse(event)
        val idEvent = "610504"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    "",
                    SearchMatchResponse::class.java)
            ).thenReturn(response)

            presenter.getSearchMatch(idEvent)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showSearchList(event)
            Mockito.verify(view).hideLoading()
        }
    }
}