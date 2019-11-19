package com.dicoding.picodiploma.sejiwaproject.features.player

import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.features.TestContextProvider
import com.dicoding.picodiploma.sejiwaproject.features.player.model.Player
import com.dicoding.picodiploma.sejiwaproject.features.player.model.PlayerDetailResponse
import com.dicoding.picodiploma.sejiwaproject.features.player.model.PlayerResponse
import com.google.gson.Gson
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PlayerPresenterTest {
    @Mock
    private lateinit var view: PlayerView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    private lateinit var presenter: PlayerPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = PlayerPresenter(view, apiRepository, gson, TestContextProvider())
    }

    @Test
    fun `test get logo player is success`() {
        val players: MutableList<Player> = mutableListOf()
        val response = PlayerResponse(players)
        val idTeam = "idTeam"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    "",
                    PlayerResponse::class.java
                )
            ).thenReturn(response)

            presenter.getPlayerLogo(idTeam)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showPlayerDetail(players)
            Mockito.verify(view).hideLoading()

        }
    }

    @Test
    fun `test get detail player is success`() {
        val player = Player(
            "133690", "34145937",
            "https://www.thesportsdb.com/images/media/player/cutout/43001238.png",
            "Mario Balotelli",
            "https://www.thesportsdb.com/images/media/player/fanart/wwtsqs1431622222.jpg",
            "Mario Balotelli Barwuah (Italian pronunciation: born Mario Barwuah; " +
                    "12 August 1990) is an Italian professional footballer who plays as a striker for " +
                    "French club Olympique de Marseille and the Italy national team. ",
            "1990-08-12", "Palermo, Italy", "Forward", "Italy"
        )

        val players: MutableList<Player> = mutableListOf(player)
        val response = PlayerDetailResponse(players)
        val idPlayer = "34145937"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    "",
                    PlayerDetailResponse::class.java
                )
            ).thenReturn(response)

            presenter.getPlayerDetail(idPlayer)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showPlayerDetail(players)
            Mockito.verify(view).hideLoading()

        }
    }
}