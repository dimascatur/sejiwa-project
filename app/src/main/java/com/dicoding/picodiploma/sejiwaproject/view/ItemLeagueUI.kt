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
                id = R.id.club_logo
                imageResource = R.drawable.arsenal_logo

            }.lparams(width = dip(120),
                height = dip(120))
            linearLayout{
                textView{
                    val clubName = "Arsenal"
                    id = R.id.name_club
                    textSize = sp(16).toFloat()
                    text = clubName
                }.lparams(width = matchParent,
                    height = wrapContent)
                textView{
                    val clubStadium = "The Emirates Stadium"
                    id = R.id.stadium_name
                    textSize = sp(14).toFloat()
                    text = clubStadium
                }.lparams(width = matchParent,
                    height = wrapContent)
                textView{
                    val clubDesc = "Arsenal Football Club is a professional football club based in Holloway, London which currently plays in the Premier League, the highest level of English football. One of the most successful clubs in English football, " +
                            "they have won 13 First Division and Premier League titles and a joint record 11 FA Cups.\n"
                    id = R.id.club_desc
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