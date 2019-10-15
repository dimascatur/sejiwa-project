package com.dicoding.picodiploma.sejiwaproject.view

import android.content.Context
import android.view.View
import com.dicoding.picodiploma.sejiwaproject.R
import org.jetbrains.anko.*


class ItemLeagueUI : AnkoComponent<Context> {

    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        return relativeLayout {
            padding = dip(16)
            imageView {
                id = R.id.league_logo
                imageResource = R.drawable.english

            }.lparams(width = dip(120),
                height = dip(120))
            linearLayout{
                textView{
                    val leagueName = "English Premier League"
                    id = R.id.name_league
                    textSize = sp(16).toFloat()
                    text = leagueName
                }.lparams(width = matchParent,
                    height = wrapContent)
                textView{
                    val leagueLocation = "England"
                    id = R.id.league_loc
                    textSize = sp(14).toFloat()
                    text = leagueLocation
                }.lparams(width = matchParent,
                    height = wrapContent)
                textView{
                    val clubDesc = "The Premier League (often referred to as the English Premier League (EPL) outside England), is the top level of the English football league system. Contested by 20 clubs, it operates on a system of promotion and relegation with the English Football League (EFL)."
                    id = R.id.league_desc
                    textSize = sp(14).toFloat()
                    text = clubDesc
                    maxLines = 2
                }.lparams(width = matchParent,
                    height = wrapContent)

            }.lparams(width = matchParent,
                height = wrapContent)
        }
    }
}