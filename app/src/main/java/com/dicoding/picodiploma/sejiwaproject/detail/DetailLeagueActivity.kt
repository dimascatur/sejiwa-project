package com.dicoding.picodiploma.sejiwaproject.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.dicoding.picodiploma.sejiwaproject.model.League
import com.dicoding.picodiploma.sejiwaproject.R
import com.dicoding.picodiploma.sejiwaproject.view.DetailLeagueUI
import org.jetbrains.anko.setContentView

class DetailLeagueActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DetailLeagueUI().setContentView(this)

        val imgLogo: ImageView = findViewById(R.id.league_logo)
        val txtName: TextView = findViewById(R.id.name_league)
        val txtDesc: TextView = findViewById(R.id.league_desc)
        val txtLocation: TextView = findViewById(R.id.league_loc)

        val nameLeague = intent.getParcelableExtra<League>(EXTRA_KEY)
        imgLogo.setImageResource(nameLeague.photo)
        txtName.text = nameLeague.name
        txtDesc.text = nameLeague.description
        txtLocation.text = nameLeague.location

    }
    companion object {
        const val EXTRA_KEY = "extra_key"
    }
}
