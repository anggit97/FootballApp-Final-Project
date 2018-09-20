package com.anggitprayogo.footballapp.fotballapp.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import com.anggitprayogo.footballapp.fotballapp.R
import com.anggitprayogo.footballapp.fotballapp.model.teams.Team
import com.anggitprayogo.footballapp.fotballapp.model.teams.TeamResponse
import com.bumptech.glide.Glide

class TeamAdapter(val datas: List<Team>,
                  val listener: (Team) -> Unit): RecyclerView.Adapter<TeamAdapter.ViewHolder>(), Filterable {

    var filterListResult: List<Team>? = null

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val tvTeam = view.findViewById<TextView>(R.id.tv_team)
        val ivTeam = view.findViewById<ImageView>(R.id.iv_team)

        fun bindItems(listener: (Team) -> Unit, data: Team) {

            tvTeam.text = data.strTeam

            Glide.with(itemView.context)
                    .load(data.strTeamBadge)
                    .into(ivTeam)

            itemView.setOnClickListener {
                listener(data)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.single_team_layout, parent, false))
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: TeamAdapter.ViewHolder, position: Int) {
        holder.bindItems(listener, datas.get(position))
    }

    override fun getFilter(): Filter {
//        return object : Filter(){
//            override fun performFiltering(constraint: CharSequence?): FilterResults {
//                val charSearch = constraint?.toString()
//                Log.d("PERFORM NOT START", "")
//                if (charSearch!!.isEmpty()){
//                    filterListResult = datas
//                    Log.d("PERFORM EMPTY", "")
//                }else{
//                    val resulList = ArrayList<Team>()
//                    for (row in datas){
//                        if (row!!.strTeam.toLowerCase().contains(charSearch.toLowerCase()))
//                            resulList.add(row)
//                    }
//                    filterListResult = resulList
//                }
//
//                val filterResults = Filter.FilterResults()
//                filterResults.values =  filterListResult
//                Log.d("PERFORM", "")
//                return filterResults
//            }
//
//            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
//                filterListResult = results!!.values as List<Team>
//                Log.d("PUBLISH", "")
//                notifyDataSetChanged()
//            }
//
//        }
        return RecyclerFilter()
    }

    inner class RecyclerFilter: Filter(){
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val charSearch = constraint?.toString()
                Log.d("PERFORM NOT START", "")
                if (charSearch!!.isEmpty()){
                    filterListResult = datas
                    Log.d("PERFORM EMPTY", "")
                }else{
                    val resulList = ArrayList<Team>()
                    for (row in datas){
                        if (row.strTeam.toLowerCase().contains(charSearch.toLowerCase()))
                            resulList.add(row)
                    }
                    filterListResult = resulList
                }

                val filterResults = Filter.FilterResults()
                filterResults.values =  filterListResult
                Log.d("PERFORM", "")
                return filterResults
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            filterListResult = results!!.values as List<Team>
            Log.d("PUBLISH", "")
            notifyDataSetChanged()
        }

    }

}