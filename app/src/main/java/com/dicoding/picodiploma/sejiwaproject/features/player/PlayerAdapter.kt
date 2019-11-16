package com.dicoding.picodiploma.sejiwaproject.features.player

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.sejiwaproject.R
import com.dicoding.picodiploma.sejiwaproject.features.player.PlayerDetailActivity.Companion.ID_PLAYER
import com.dicoding.picodiploma.sejiwaproject.features.player.model.Player
import org.jetbrains.anko.startActivity

class PlayerAdapter(private val grid: List<Player>) :
    RecyclerView.Adapter<PlayerAdapter.MatchViewHolder>() {

    override fun onCreateViewHolder( parent: ViewGroup, viewType: Int
    ): MatchViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_player, parent, false)
        return MatchViewHolder(view)
    }

    override fun getItemCount(): Int = grid.size

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val player = grid[position]
        holder.playerName.text = player.playerName

        Glide.with(holder.itemView)
            .load(player.playerImg)
            .apply(RequestOptions().error(R.drawable.ic_player_error))
            .into(holder.playerImg)

        holder.itemView.setOnClickListener {
            it.context.startActivity<PlayerDetailActivity>(ID_PLAYER to player.playerId)
        }
    }

    inner class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playerImg: ImageView = itemView.findViewById(R.id.player_image)
        val playerName: TextView = itemView.findViewById(R.id.player_name)
    }
}