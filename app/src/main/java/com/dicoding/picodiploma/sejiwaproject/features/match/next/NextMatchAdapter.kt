package com.dicoding.picodiploma.sejiwaproject.features.match.next

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.sejiwaproject.R
import com.dicoding.picodiploma.sejiwaproject.features.match.detail.DetailMatchActivity
import com.dicoding.picodiploma.sejiwaproject.features.match.detail.DetailMatchActivity.Companion.EXTRA_ID
import com.dicoding.picodiploma.sejiwaproject.features.match.next.model.NextMatch
import com.dicoding.picodiploma.sejiwaproject.features.team.DetailTeamActivity
import com.dicoding.picodiploma.sejiwaproject.features.team.DetailTeamActivity.Companion.EXTRA_TEAM
import org.jetbrains.anko.startActivity


class NextMatchAdapter(private val list: MutableList<NextMatch>) :
    RecyclerView.Adapter<NextMatchAdapter.MatchViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_next_match, parent, false)
        return MatchViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    fun addNextMatch(nextMatch: NextMatch) {
        list.add(nextMatch)
        notifyItemInserted(list.size -1)
    }
    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val match = list[position]
        holder.homeTeam.text = match.teamHome
        holder.awayTeam.text = match.teamAway
        holder.dateMatch.text = match.dateMatch
        Glide.with(holder.itemView)
            .load(match.badgeHome)
            .into(holder.homeLogo)
        holder.homeLogo.setOnClickListener {
            it.context.startActivity<DetailTeamActivity>(EXTRA_TEAM to match.homeId)
        }

        Glide.with(holder.itemView)
            .load(match.badgeAway)
            .into(holder.awayLogo)
        holder.awayLogo.setOnClickListener {
            it.context.startActivity<DetailTeamActivity>(EXTRA_TEAM to match.awayId)
        }

        holder.itemView.setOnClickListener {
            it.context.startActivity<DetailMatchActivity>(EXTRA_ID to match.matchId)

        }
    }

    inner class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val homeTeam: TextView = itemView.findViewById(R.id.team_home)
        val awayTeam: TextView = itemView.findViewById(R.id.team_away)
        val dateMatch: TextView = itemView.findViewById(R.id.date_match)
        val homeLogo: ImageView = itemView.findViewById(R.id.home_logo)
        val awayLogo: ImageView = itemView.findViewById(R.id.away_logo)
    }
}

