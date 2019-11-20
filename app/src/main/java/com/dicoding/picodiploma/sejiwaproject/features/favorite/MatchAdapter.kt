package com.dicoding.picodiploma.sejiwaproject.features.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.sejiwaproject.R
import com.dicoding.picodiploma.sejiwaproject.db.Favorite


class FavoriteNextAdapter(private val favorite: List<Favorite>, private val listener:
    (Favorite) -> Unit): RecyclerView.Adapter<FavoriteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_favorite, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun getItemCount(): Int = favorite.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(favorite[position], listener)
    }
}

class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val teamHome: TextView = view.findViewById(R.id.team_home)
    private val teamAway: TextView = view.findViewById(R.id.team_away)
    private val dateMatch: TextView = view.findViewById(R.id.date_match)
    private val badgeHome: ImageView = view.findViewById(R.id.home_logo)
    private val badgeAway: ImageView = view.findViewById(R.id.away_logo)


    fun bindItem(favorite: Favorite, listener: (Favorite) -> Unit) {
        teamHome.text = favorite.teamHome
        teamAway.text = favorite.teamAway
        dateMatch.text = favorite.dateMatch
        Glide.with(badgeHome)
            .load(favorite.badgeHome)
            .into(badgeHome)

        Glide.with(badgeAway)
            .load(favorite.badgeAway)
            .into(badgeAway)
        itemView.setOnClickListener { listener(favorite) }
    }
}