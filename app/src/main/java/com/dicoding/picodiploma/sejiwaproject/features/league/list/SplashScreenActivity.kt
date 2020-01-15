package com.dicoding.picodiploma.sejiwaproject.features.league.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.dicoding.picodiploma.sejiwaproject.R

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            startActivity(
                Intent(
                    this,
                    LeagueListActivity::class.java
                )
            )
        }, 2000)
    }
}
