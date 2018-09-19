package com.anggitprayogo.footballapp.fotballapp.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.anggitprayogo.footballapp.fotballapp.R
import com.anggitprayogo.footballapp.fotballapp.model.favourite.FavouriteEvent

class FavouriteEventAdapter(val datas: List<FavouriteEvent>,
                            val listener: (FavouriteEvent) -> Unit): RecyclerView.Adapter<FavouriteEventAdapter.ViewHolder>(){
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        val tvDate = view.findViewById<TextView>(R.id.tv_date)
        val tvHomeName = view.findViewById<TextView>(R.id.tv_home_team_name)
        val tvHomeScore = view.findViewById<TextView>(R.id.tv_home_score)
        val tvAwayName = view.findViewById<TextView>(R.id.tv_away_team_name)
        val tvAwayScore = view.findViewById<TextView>(R.id.tv_away_score)

        fun bindItems(listener: (FavouriteEvent) -> Unit, matches: FavouriteEvent) {
            tvDate.text = matches.strDate
            tvAwayName.text = matches.strHomeScore
            tvAwayScore.text = matches.strAwayScore
            tvHomeName.text = matches.strHomeName
            tvHomeScore.text = matches.strAwayName

            itemView.setOnClickListener {
                listener(matches)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteEventAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_match_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: FavouriteEventAdapter.ViewHolder, position: Int) {
        holder.bindItems(listener, datas.get(position))
    }
}