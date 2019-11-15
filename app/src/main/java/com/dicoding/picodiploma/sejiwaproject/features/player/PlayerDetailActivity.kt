package com.dicoding.picodiploma.sejiwaproject.features.player

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.sejiwaproject.R
import com.dicoding.picodiploma.sejiwaproject.commons.api.ApiRepository
import com.dicoding.picodiploma.sejiwaproject.commons.utils.invisible
import com.dicoding.picodiploma.sejiwaproject.commons.utils.visible
import com.dicoding.picodiploma.sejiwaproject.features.player.model.Player
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_player_detail.*
import kotlinx.android.synthetic.main.activity_player_detail.player_name

class PlayerDetailActivity : AppCompatActivity(),
    PlayerView {
    private var players: MutableList<Player> = mutableListOf()
    private lateinit var presenter: PlayerPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        val playerDetail = intent.getStringExtra(ID_PLAYER)


        val request = ApiRepository()
        val gson = Gson()
        presenter = PlayerPresenter(
            this,
            request,
            gson
        )
        presenter.getPlayerDetail(playerDetail)
    }

    companion object {
        const val ID_PLAYER = "id_player"
    }

    override fun showLoading() {
        progress_bar.visible()
    }

    override fun hideLoading() {
        progress_bar.invisible()
    }

    override fun showPlayerDetail(data: List<Player>) {
        if (data[0].playerId.isNullOrEmpty()) {
            player_name.invisible()
            player_born.invisible()
            player_description.invisible()
            player_nationality.invisible()
            player_icon.invisible()
            player_background.invisible()

        } else {

            player_name.text = data[0].playerName
            player_born.text = data[0].playerBorn
            player_description.text = data[0].playerDescription
            player_nationality.text = data[0].playerNationality

            Glide.with(this)
                .load(data[0].playerImg)
                .apply(RequestOptions().override(550, 550))
                .into(player_icon)

            Glide.with(this)
                .load(data[0].playerBackground)
                .apply(RequestOptions().override(550, 550))
                .into(player_background)
        }

        players.clear()
        players.addAll(data)
    }
}
