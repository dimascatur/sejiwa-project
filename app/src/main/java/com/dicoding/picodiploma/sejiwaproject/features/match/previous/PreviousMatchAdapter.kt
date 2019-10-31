package com.dicoding.picodiploma.sejiwaproject.features.match.previousMatch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.sejiwaproject.R
import com.dicoding.picodiploma.sejiwaproject.features.match.detailMatch.DetailMatchActivity
import com.dicoding.picodiploma.sejiwaproject.features.match.detailMatch.DetailMatchActivity.Companion.EXTRA_ID
import com.dicoding.picodiploma.sejiwaproject.features.match.previousMatch.model.Matchs
import org.jetbrains.anko.startActivity


class PreviousMatchAdapter(private val list: List<Matchs>) : RecyclerView.Adapter<PreviousMatchAdapter.MatchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_previous_match, parent, false)
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
        holder.dateMatch.text = match.dateMatch
        Glide.with(holder.itemView)
            .load(match.badgeHome)
            .into(holder.homeLogo)

        Glide.with(holder.itemView)
            .load(match.badgeAway)
            .into(holder.awayLogo)

        holder.itemView.setOnClickListener {
            it.context.startActivity<DetailMatchActivity>(EXTRA_ID to match.matchId)
        }
    }

    inner class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val matchTitle: TextView = itemView.findViewById(R.id.match_title)
        val homeTeam: TextView = itemView.findViewById(R.id.team_home)
        val awayTeam: TextView = itemView.findViewById(R.id.team_away)
        val homeScore: TextView = itemView.findViewById(R.id.home_score)
        val awayScore: TextView = itemView.findViewById(R.id.away_score)
        val dateMatch: TextView = itemView.findViewById(R.id.date_match)
        val homeLogo: ImageView = itemView.findViewById(R.id.home_logo)
        val awayLogo: ImageView = itemView.findViewById(R.id.away_logo)
    }

}