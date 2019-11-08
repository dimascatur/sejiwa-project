package com.dicoding.picodiploma.sejiwaproject.features.favorite.next

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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

    fun bindItem(favorite: Favorite, listener: (Favorite) -> Unit) {
        teamHome.text = favorite.teamHome
        teamAway.text = favorite.teamAway
        dateMatch.text = favorite.dateMatch
        itemView.setOnClickListener { listener(favorite) }
    }
}