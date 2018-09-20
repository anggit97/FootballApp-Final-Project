package com.anggitprayogo.footballapp.fotballapp.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.anggitprayogo.footballapp.fotballapp.R
import com.anggitprayogo.footballapp.fotballapp.model.searchevent.SearchEvent
import org.jetbrains.anko.find
import org.jetbrains.anko.sdk25.coroutines.onClick

class SearchMatchAdapter (val events: List<SearchEvent>,
                          val listener: (SearchEvent) -> Unit) : RecyclerView.Adapter<SearchMatchAdapter.LastMatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastMatchViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.single_match_layout, parent, false)
        return LastMatchViewHolder(v)

    }

    override fun getItemCount(): Int = events.size

    override fun onBindViewHolder(holder: LastMatchViewHolder, position: Int) {
        holder.bindItem(events[position], listener)
    }


    class LastMatchViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val date: TextView = view.find(R.id.tv_date)
        val teamHome: TextView = view.find(R.id.tv_home_team_name)
        val teamAway: TextView = view.find(R.id.tv_away_team_name)
        val scoreHome: TextView? = view.find(R.id.tv_home_score)
        val scoreAway: TextView? = view.find(R.id.tv_away_score)


        fun bindItem(event: SearchEvent, listener: (SearchEvent) -> Unit) {
            date.text = event.strDate
            teamHome.text = event.strHomeTeam
            teamAway.text = event.strAwayTeam
            scoreHome?.text = event.intHomeScore
            scoreAway?.text = event.intAwayScore
            itemView.onClick { listener(event) }
        }
    }
}