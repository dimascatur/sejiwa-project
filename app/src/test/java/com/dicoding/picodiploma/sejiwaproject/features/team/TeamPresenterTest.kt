package com.dicoding.picodiploma.sejiwaproject.features.team

import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.db.MyDatabaseOpenHelper
import com.dicoding.picodiploma.sejiwaproject.features.TestContextProvider
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

class TeamPresenterTest {
    @Mock
    private lateinit var view: TeamView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var apiResponse: Deferred<String>

    @Mock
    private lateinit var database: MyDatabaseOpenHelper


    private lateinit var presenter: TeamPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = TeamPresenter(view, apiRepository, gson, database, TestContextProvider())
    }

    @Test
    fun `test get detail team is success`() {
        val team = Team(
            "133604", "Arsenal",
            "https://www.thesportsdb.com/images/media/team/stadium/qpuxrr1419371354.jpg",
            "https://www.thesportsdb.com/images/media/team/badge/a1af2i1557005128.png",
            "1892",
            "Arsenal Football Club is a professional football club based in Holloway, " +
                    "London which currently plays in the Premier League, the highest level of English football. " +
                    "One of the most successful clubs in English football, " +
                    "they have won 13 First Division and Premier League titles and a joint record 11 FA Cups." +
                    "\\r\\n\\r\\nArsenal's success has been particularly consistent: the club has accumulated the" +
                    " second most points in English top-flight football, hold the ongoing record for the longest " +
                    "uninterrupted period in the top flight, and would be placed first in an aggregated" +
                    "league of the entire 20th century. Arsenal is the second side to complete an English top-flight " +
                    "season unbeaten (in the 2003–04 season), playing almost twice as many matches " +
                    "as the previous invincibles Preston North End in the 1888–89 season.\\r\\n\\r\\nArsenal was " +
                    "founded in 1886 in Woolwich and in 1893 became the first club from the south of England to join" +
                    " the Football League. In 1913, they moved north across the city to Arsenal Stadium in Highbury. In the 1930s," +
                    " they won five League Championship titles and two FA Cups. After a lean period in the post-war years they won the League and FA Cup Double, " +
                    "in the 1970–71 season, and in the 1990s and first decade of the 21st century, won two more Doubles and reached the 2006 UEFA Champions League Final. " +
                    "Since neighbouring Tottenham Hotspur, the two clubs have had a fierce rivalry," +
                    " the North London derby.\\r\\n\\r\\nArsenal have one of the highest incomes and " +
                    "largest fanbases in the world. The club was named the fifth most valuable association " +
                    "football club in the world, valued at £1.3 billion in 2014.",
            "https://www.thesportsdb.com/images/media/team/logo/q2mxlz1512644512.png",
            "https://www.thesportsdb.com/images/media/team/jersey/2019-133604-Jersey.png"
        )
        val teams: List<Team> = listOf(team)
        val response = TeamResponse(teams)
        val idTeam = "133604"

        runBlocking {
            Mockito.`when`(apiRepository.doRequestAsync(ArgumentMatchers.anyString()))
                .thenReturn(apiResponse)
            Mockito.`when`(apiResponse.await()).thenReturn("")
            Mockito.`when`(
                gson.fromJson(
                    "",
                    TeamResponse::class.java
                )
            ).thenReturn(response)

            presenter.getDetailTeam(idTeam)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showDetailTeam(teams.first())
            Mockito.verify(view).hideLoading()
        }
    }
}