package com.dicoding.picodiploma.sejiwaproject

object LeagueData {
    private val leagueNames = arrayListOf("Arsenal",
        "Aston Villa",
        "Bournemouth",
        "Brighton",
        "Burnley",
        "Chelsea",
        "Crystal Palace",
        "Everton")

    private val leagueDescription = arrayListOf("Arsenal Football Club is a professional football club based in Holloway, London which currently plays in the Premier League, the highest level of English football. One of the most successful clubs in English football, they have won 13 First Division and Premier League titles and a joint record 11 FA Cups.\n",
        "Aston Villa Football Club (also known as Villa, The Villa, The Villans, The Lions) are a professional football club based in Witton, Birmingham, who play in the Premier League, the highest level of English football. Founded in 1874, they have played at their current home ground, Villa Park, " +
                "since 1897. Aston Villa were founder members of The Football League in 1888. They were also founder members of the Premier League in 1992, and have remained there ever since. The club were floated by the previous owner and chairman Doug Ellis, but in 2006 full control was acquired by American businessman Randy Lerner. Lerner announced on 12 May 2014 that he is selling the club.",
        "A.F.C. Bournemouth is a football club playing in the Championship, the second tier in the English football league system. The club plays at Dean Court in Kings Park, Boscombe, Bournemouth, Dorset and have been in existence since 1899.\n",
        "Brighton and Hove Albion Football Club is an English football club based in the coastal city of Brighton & Hove, East Sussex. It is often referred to just as Brighton. They currently play in the Football League Championship, the second tier of the English football league system.",
        "Burnley Football Club are a professional football club based in Burnley, Lancashire, who play in the Premier League, the highest level of English football. Nicknamed The Clarets, due to the dominant colour of their home shirts, they were one of the founder members of the Football League in 1888. " +
                "The club colours of claret and blue were adopted in 1910 in tribute to the dominant club of English football at the time, Aston Villa. It was thought the colours might lift and inspire Burnley to emulate the aforementioned side. Their home ground since 1883 has been Turf Moor.\n",
        "Chelsea Football Club are a professional football club based in Fulham, London, who play in the Premier League, the highest level of English football. Founded in 1905, the club have spent most of their history in the top tier of English football. The club's home ground is the 41,837-seat Stamford Bridge stadium, where they have played since their establishment.\n",
        "Crystal Palace Football Club is an English professional football club based in South Norwood, London. They currently play in the highest level in English football, the Premier League. Since 1964, the club have mostly played in the top two leagues of English football. The club was founded in 1905 at the site of the famous Crystal Palace Exhibition building by the owners of the FA Cup Final stadium, who wanted their own team to play at the historic venue." +
                " Palace applied to be elected to The Football League, but this was rejected and they instead joined the Southern Football League Second Division, playing home games at The Crystal Palace, inspiration for the club's initial nickname, \"The Glaziers\". Palace won the Division and promotion in their first season, and played in the Southern League First Division for the next fifteen years.\n",
        "Everton Football Club are an English professional football club based in Liverpool, and currently playing in the Premier League. The club have competed in the top division for a record 110 seasons (missing only four seasons, in the second tier) and have won the League Championship nine times.")

    private val leagueStadium = arrayListOf("The Emirates Stadium",
        "Villa Park",
        "Dean Court",
        "Falmer Stadium",
        "Turf Moor",
        "Stamford Bridge",
        "Selhurst Park",
        "Goodison Park")

    private val leagueCoach = arrayListOf("Unai Emery",
        "Dean Smith",
        "Eddie Howe",
        "Graham Potter",
        "Sean Dyche",
        "Frank Lampard",
        "Roy Hodgson",
        "Marco Silva")

    private val leagueProfile = intArrayOf(R.drawable.arsenal_coach,
        R.drawable.aston_coach,
        R.drawable.bournemouth_coach,
        R.drawable.brighton_coach,
        R.drawable.burnley_coach,
        R.drawable.chelsea_coach,
        R.drawable.crystal_coach,
        R.drawable.everton_coach)

    private val leagueLogo = intArrayOf(R.drawable.arsenal_logo,
        R.drawable.aston_logo,
        R.drawable.bournemouth_logo,
        R.drawable.brightonlogo,
        R.drawable.burnley_logo,
        R.drawable.chelsea_logo,
        R.drawable.crystal_logo,
        R.drawable.everton_logo)

    private val leagueBackground = intArrayOf(R.drawable.arsenal_background,
        R.drawable.aston_background,
        R.drawable.aston_background,
        R.drawable.brighton_background,
        R.drawable.burnley_background,
        R.drawable.chelsea_background,
        R.drawable.crystal_background,
        R.drawable.everton_background)

    val listData: ArrayList<League>
    get() {

        val list = arrayListOf<League>()
        for (position in leagueNames.indices) {
            val league = League()
            league.name = leagueNames[position]
            league.description = leagueDescription[position]
            league.stadium = leagueStadium[position]
            league.coach = leagueCoach[position]
            league.profileCoach = leagueProfile[position]
            league.logo = leagueLogo[position]
            league.background = leagueBackground[position]
            list.add(league)
        }
        return list
    }
}