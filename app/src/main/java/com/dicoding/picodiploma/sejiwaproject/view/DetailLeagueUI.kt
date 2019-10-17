package com.dicoding.picodiploma.sejiwaproject.view

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import com.dicoding.picodiploma.sejiwaproject.R
import org.jetbrains.anko.*

class DetailLeagueUI : AnkoComponent<Context> {
    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        return linearLayout {
            scrollView {
                verticalLayout {
                    imageView {
                        padding = dip(16)
                        id = R.id.league_logo
                    }.lparams(
                        width = matchParent,
                        height = wrapContent
                    )
                    textView {
                        id = R.id.name_league
                        textSize = 20f
                        typeface = Typeface.DEFAULT_BOLD
                        textColor = Color.BLACK
                    }.lparams(
                        width = matchParent,
                        height = wrapContent
                    )
                    textView {
                        id = R.id.league_loc
                        textSize = 16f
                    }.lparams(
                        width = matchParent,
                        height = wrapContent
                    )
                    textView {
                        id = R.id.league_desc
                        textSize = 16f
                        maxLines = 3
                    }.lparams(
                        width = matchParent,
                        height = wrapContent
                    )
                }.lparams(
                    width = matchParent,
                    height = wrapContent
                ) {
                    marginStart = 32
                }
            }.lparams(
                width = matchParent,
                height = wrapContent)
        }
    }
}