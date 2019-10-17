package com.dicoding.picodiploma.sejiwaproject.view

import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.sejiwaproject.MainActivity
import com.dicoding.picodiploma.sejiwaproject.R
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivityUI : AnkoComponent<MainActivity> {
    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        recyclerView {
            id = R.id.rv_league
            layoutManager = LinearLayoutManager(context)
            lparams(width = matchParent, height = matchParent)
            padding = dip(16)
            clipToPadding = false

        }
    }
}