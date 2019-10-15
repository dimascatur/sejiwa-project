package com.dicoding.picodiploma.sejiwaproject.db

import com.dicoding.picodiploma.sejiwaproject.R
import com.dicoding.picodiploma.sejiwaproject.model.League

object LeagueData {
    private val leagueNames = arrayListOf("English Premier League",
        "French Ligue 1",
        "BundesLiga",
        "Italian Serie A",
        "Portuguese Primeira Li",
        "Scottish Premier Leagu",
        "La Liga",
        "American Major League")

    private val leagueDescription = arrayListOf("The Premier League (often referred to as the English Premier League (EPL) outside England), is the top level of the English football league system. Contested by 20 clubs, it operates on a system of promotion and relegation with the English Football League (EFL).\n",
        "Ligue 1 (French pronunciation: League 1, formerly known as Division 1), is the French professional league for association football clubs. It is the country's primary football competition and serves as the top division of the French football league system. Ligue 1 is one of two divisions making up the Ligue de Football Professionnel, the other being Ligue 2.",
        "The Bundesliga, is a professional association football league in Germany and the football league with the highest average stadium attendance worldwide. At the top of the German football league system, the Bundesliga is Germany's primary football competition.",
        "Serie A, also called Serie A TIM due to sponsorship by Telecom Italia, is a professional league competition for football clubs located at the top of the Italian football league system and has been operating for over eighty years since the 1929–30 season. It had been organized by Lega Calcio until 2010, but a new league, the Lega Serie A, was created for the 2010–11 season.",
        "The Primeira Liga (First League; Portuguese pronunciation: ), formerly called Primeira Divisão), is the top professional association football division of the Portuguese football league system.\n",
        "The Scottish Premiership is the top division of the Scottish Professional Football League, the league competition for professional football clubs in Scotland. The Scottish Premiership was established in July 2013, after the Scottish Professional Football League was formed by a merger of the Scottish Premier League and Scottish Football League.",
        "The Primera División, commonly known as La Liga and as La Liga Santander for sponsorship reasons, is the top professional association football division of the Spanish football league system.",
        "Major League Soccer (MLS) is a professional soccer league representing the sport's highest level in both the United States and Canada. MLS constitutes one of the major professional sports leagues of the United States and Canada. The league is composed of 20 teams—17 in the U.S. and 3 in Canada.")

    private val leagueLocation = arrayListOf("England",
        "France",
        "Germany",
        "Italia",
        "Portugal",
        "Scotland",
        "Spanyol",
        "USA")

    private val leagueLogo = intArrayOf(
        R.drawable.english,
        R.drawable.france,
        R.drawable.germany,
        R.drawable.italia,
        R.drawable.portugal,
        R.drawable.scotland,
        R.drawable.spanyol,
        R.drawable.usa
    )

    val listData: ArrayList<League>
    get() {

        val list = arrayListOf<League>()
        for (position in leagueNames.indices) {
            val league = League()
            league.name = leagueNames[position]
            league.description = leagueDescription[position]
            league.location = leagueLocation[position]
            league.photo = leagueLogo[position]
            list.add(league)
        }
        return list
    }
}