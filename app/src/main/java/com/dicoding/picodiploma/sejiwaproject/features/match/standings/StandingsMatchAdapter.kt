package com.dicoding.picodiploma.sejiwaproject.features.match.standings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.sejiwaproject.R
import com.dicoding.picodiploma.sejiwaproject.features.match.standings.model.StandingsMatch
import com.dicoding.picodiploma.sejiwaproject.features.team.TeamActivity
import com.dicoding.picodiploma.sejiwaproject.features.team.TeamActivity.Companion.EXTRA_TEAM
import org.jetbrains.anko.startActivity


class StandingsMatchAdapter(private var list: MutableList<StandingsMatch>) : RecyclerView.Adapter<StandingsMatchAdapter.MatchViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MatchViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_standings, parent, false)
        return MatchViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    fun addStandingsMatch(standingsMatch: StandingsMatch){
        list.add(standingsMatch)
        notifyItemInserted(list.size - 1)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val match = list[position]
        holder.teamName.text = match.teamName
        holder.teamPlayed.text = match.teamPlayed
        holder.teamGoals.text = match.teamGoal
        holder.teamWin.text = match.teamWin
        holder.teamDraw.text = match.teamDraw
        holder.teamLoss.text = match.teamLose
        holder.teamPoint.text = match.teamPoint

        Glide.with(holder.itemView)
            .load(match.teamBadge)
            .into(holder.teamLogo)
        holder.teamLogo.setOnClickListener {
            it.context.startActivity<TeamActivity>(EXTRA_TEAM to match.teamId)
        }

    }

    class MatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val teamName: TextView = itemView.findViewById(R.id.team_name)
        val teamPlayed: TextView = itemView.findViewById(R.id.team_played)
        val teamGoals: TextView = itemView.findViewById(R.id.team_goals)
        val teamWin: TextView = itemView.findViewById(R.id.team_win)
        val teamDraw: TextView = itemView.findViewById(R.id.team_draw)
        val teamLoss: TextView = itemView.findViewById(R.id.team_loss)
        val teamPoint: TextView = itemView.findViewById(R.id.team_point)
        val teamLogo: ImageView = itemView.findViewById(R.id.team_logo)

    }

}
