package com.anggitprayogo.footballapp.fotballapp.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.anggitprayogo.footballapp.fotballapp.R
import com.anggitprayogo.footballapp.fotballapp.model.eventspastleague.EventPast

class EventPastLeagueAdapter(val matches: List<EventPast>,
                             val listener: (EventPast) -> Unit): RecyclerView.Adapter<EventPastLeagueAdapter.ViewHolder>() {

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {

        val tvDate = view.findViewById<TextView>(R.id.tv_date)
        val tvHomeName = view.findViewById<TextView>(R.id.tv_home_team_name)
        val tvHomeScore = view.findViewById<TextView>(R.id.tv_home_score)
        val tvAwayName = view.findViewById<TextView>(R.id.tv_away_team_name)
        val tvAwayScore = view.findViewById<TextView>(R.id.tv_away_score)

        fun bindItem(listener: (EventPast) -> Unit, matches: EventPast) {

            tvDate.text = matches.dateEvent
            tvAwayName.text = matches.strAwayTeam
            tvAwayScore.text = matches.intAwayScore
            tvHomeName.text = matches.strHomeTeam
            tvHomeScore.text = matches.intHomeScore

            itemView.setOnClickListener {
                listener(matches)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventPastLeagueAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_match_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = matches.size

    override fun onBindViewHolder(holder: EventPastLeagueAdapter.ViewHolder, position: Int) {
        holder.bindItem(listener, matches.get(position));
    }
}