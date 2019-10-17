package com.dicoding.picodiploma.sejiwaproject.view
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import android.widget.LinearLayout
import com.dicoding.picodiploma.sejiwaproject.R
import org.jetbrains.anko.*


class ItemLeagueUI : AnkoComponent<Context> {

    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        return linearLayout {
            padding = dip(16)
            imageView {
                id = R.id.league_logo
                imageResource = R.drawable.english

            }.lparams(width = dip(80),
                height = dip(80))
            verticalLayout{
                textView{
                    id = R.id.name_league
                    textSize = 17f
                    typeface = Typeface.DEFAULT_BOLD
                    textColor = Color.BLACK
                }.lparams(width = matchParent,
                    height = wrapContent)
                textView{
                    id = R.id.league_loc
                    textSize = 12f
                }.lparams(width = matchParent,
                    height = wrapContent)
                textView{
                    id = R.id.league_desc
                    textSize = 12f
                    maxLines = 2
                }.lparams(width = matchParent,
                    height = wrapContent)

            }.lparams(width = matchParent,
                height = wrapContent){
                orientation = LinearLayout.HORIZONTAL
                marginStart = 32
            }
        }
    }
}