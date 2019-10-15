package com.dicoding.picodiploma.sejiwaproject.view

import android.content.Context
import android.view.View
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.linearLayout

class DetailLeagueUI : AnkoComponent<Context> {
    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        return linearLayout()
    }
}