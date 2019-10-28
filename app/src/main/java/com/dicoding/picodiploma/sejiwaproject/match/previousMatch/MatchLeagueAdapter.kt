package com.dicoding.picodiploma.sejiwaproject.match

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.sejiwaproject.R
import com.dicoding.picodiploma.sejiwaproject.model.previousMatch.Matchs

class MatchLeagueAdapter(private val list: List<Matchs>) : RecyclerView.Adapter<MatchLeagueAdapter.MatchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false)
        return MatchViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val match = list[position]
        holder.matchTitle.text = match.matchTitle
        holder.homeTeam.text = match.teamHome
        holder.awayTeam.text = match.teamAway
        holder.homeScore.text = match.homeScore
        holder.awayScore.text = match.awayScore
    }

    inner class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val matchTitle: TextView = itemView.findViewById(R.id.match_title)
        val homeTeam: TextView = itemView.findViewById(R.id.team_home)
        val awayTeam: TextView = itemView.findViewById(R.id.team_away)
        val homeScore: TextView = itemView.findViewById(R.id.home_score)
        val awayScore: TextView = itemView.findViewById(R.id.away_score)



    }

}