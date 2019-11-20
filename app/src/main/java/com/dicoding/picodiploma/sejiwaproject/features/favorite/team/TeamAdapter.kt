package com.dicoding.picodiploma.sejiwaproject.features.favorite.team

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.sejiwaproject.R
import com.dicoding.picodiploma.sejiwaproject.db.FavoriteTeam

class TeamAdapter(
    private val favorite: List<FavoriteTeam>, private val listener:
        (FavoriteTeam) -> Unit
) : RecyclerView.Adapter<TeamAdapter.FavoriteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_favorite_team, parent, false)
        return FavoriteViewHolder(
            view
        )
    }

    override fun getItemCount(): Int = favorite.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(favorite[position], listener)
    }

    class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val teamName: TextView = view.findViewById(R.id.team_name)
        private val teamLogo: ImageView = view.findViewById(R.id.team_logo)


        fun bindItem(favorite: FavoriteTeam, listener: (FavoriteTeam) -> Unit) {
            teamName.text = favorite.teamName
            Glide.with(teamLogo)
                .load(favorite.teamBadge)
                .into(teamLogo)
            itemView.setOnClickListener {
                listener(favorite)
            }
        }
    }
}
