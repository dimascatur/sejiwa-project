package com.dicoding.picodiploma.sejiwaproject

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.sejiwaproject.detail.DetailLeagueActivity
import com.dicoding.picodiploma.sejiwaproject.model.League
import com.dicoding.picodiploma.sejiwaproject.view.ItemLeagueUI
import org.jetbrains.anko.AnkoContext

class LeagueAdapter(private val list: ArrayList<League>) :
    RecyclerView.Adapter<LeagueAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(ItemLeagueUI().createView(AnkoContext.create(parent.context)))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val leagues = list[position]

        Glide.with(holder.itemView.context)
            .load(leagues.photo)
            .apply(RequestOptions().override(80, 80))
            .into(holder.imgLogo)

        holder.txtName.text = leagues.name
        holder.txtDesc.text = leagues.description
        holder.txtLocation.text = leagues.location
        holder.itemView.setOnClickListener {
            val detailLeagueActivity = Intent(it.context, DetailLeagueActivity::class.java)
            detailLeagueActivity.putExtra(DetailLeagueActivity.EXTRA_KEY, list[holder.adapterPosition])
            it.context.startActivity(detailLeagueActivity)
        }

    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgLogo: ImageView = itemView.findViewById(R.id.league_logo) as ImageView
        val txtName: TextView = itemView.findViewById(R.id.name_league) as TextView
        val txtDesc: TextView = itemView.findViewById(R.id.league_desc) as TextView
        val txtLocation: TextView = itemView.findViewById(R.id.league_loc) as TextView


    }
}