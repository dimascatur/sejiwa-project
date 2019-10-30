package com.dicoding.picodiploma.sejiwaproject.match.nextMatch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.sejiwaproject.R
import com.dicoding.picodiploma.sejiwaproject.detailMatch.DetailMatchActivity
import com.dicoding.picodiploma.sejiwaproject.detailMatch.DetailMatchActivity.Companion.EXTRA_ID
import com.dicoding.picodiploma.sejiwaproject.model.nextMatch.NextMatch
import org.jetbrains.anko.startActivity


class NextMatchAdapter(private val list: List<NextMatch>) :
    RecyclerView.Adapter<NextMatchAdapter.MatchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_next_match, parent, false)
        return MatchViewHolder(view)
    }

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

