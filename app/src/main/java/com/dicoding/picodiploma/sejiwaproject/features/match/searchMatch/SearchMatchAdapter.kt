package com.dicoding.picodiploma.sejiwaproject.features.match.searchMatch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.sejiwaproject.R
import com.dicoding.picodiploma.sejiwaproject.features.match.detailMatch.DetailMatchActivity
import com.dicoding.picodiploma.sejiwaproject.features.match.detailMatch.DetailMatchActivity.Companion.EXTRA_ID
import com.dicoding.picodiploma.sejiwaproject.features.match.searchMatch.model.SearchMatch
import org.jetbrains.anko.startActivity

class SearchMatchAdapter(private val list: List<SearchMatch>) : RecyclerView.Adapter<SearchMatchAdapter.MatchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_search_match, parent, false)
        return MatchViewHolder(view)    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val match = list[position]
        holder.homeTeam.text = match.teamHome
        holder.awayTeam.text = match.teamAway
        holder.dateMatch.text = match.dateMatch
        holder.itemView.setOnClickListener {
            it.context.startActivity<DetailMatchActivity>(EXTRA_ID to match.matchId)
        }
    }

    inner class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val homeTeam: TextView = itemView.findViewById(R.id.team_home)
        val awayTeam: TextView = itemView.findViewById(R.id.team_away)
        val dateMatch: TextView = itemView.findViewById(R.id.date_match)

    }
}
