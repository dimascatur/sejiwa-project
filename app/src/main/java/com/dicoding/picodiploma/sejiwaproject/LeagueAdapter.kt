package com.dicoding.picodiploma.sejiwaproject

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.sejiwaproject.view.ItemLeagueUI
import org.jetbrains.anko.AnkoContext

class LeagueAdapter(private val list: ArrayList<League>) :
    RecyclerView.Adapter<LeagueAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(ItemLeagueUI().createView(AnkoContext.create(parent.context)))
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
//        val club = list[position]

    }

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgLogo: ImageView =  itemView.findViewById(R.id.club_logo) as ImageView
        val txtName: TextView = itemView.findViewById(R.id.coach_name) as TextView
        val txtStadium: TextView = itemView.findViewById(R.id.stadium_name) as TextView
        val txtDesc: TextView = itemView.findViewById(R.id.club_desc) as TextView



    }
}