package com.dicoding.picodiploma.sejiwaproject.features.league.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.sejiwaproject.R
import com.dicoding.picodiploma.sejiwaproject.features.league.detail.DetailLeagueActivity
import com.dicoding.picodiploma.sejiwaproject.features.league.detail.DetailLeagueActivity.Companion.EXTRA_KEY
import org.jetbrains.anko.startActivity

class LeagueAdapter(private val list: ArrayList<League>) :
    RecyclerView.Adapter<LeagueAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_leagues, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount() = list.size


    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val leagues = list[position]

        Glide.with(holder.itemView.context)
            .load(leagues.photo).into(holder.imgLogo)

        holder.txtName.text = leagues.name
        holder.txtLocation.text = leagues.location
        holder.itemView.setOnClickListener {
            it.context.startActivity<DetailLeagueActivity>(EXTRA_KEY to leagues.id)
        }

    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgLogo: ImageView = itemView.findViewById(R.id.league_logo)
        val txtName: TextView = itemView.findViewById(R.id.name_league)
        val txtLocation: TextView = itemView.findViewById(R.id.league_loc)


    }
}