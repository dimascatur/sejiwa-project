package com.dicoding.picodiploma.sejiwaproject.features.player

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.sejiwaproject.R
import com.dicoding.picodiploma.sejiwaproject.features.player.model.Player

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
            .into(holder.playerImg)
    }

    inner class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val playerImg: ImageView = itemView.findViewById(R.id.player_image)
        val playerName: TextView = itemView.findViewById(R.id.player_name)
    }
}