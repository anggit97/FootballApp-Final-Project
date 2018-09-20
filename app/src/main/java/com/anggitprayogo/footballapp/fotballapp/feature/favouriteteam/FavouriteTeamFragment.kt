package com.anggitprayogo.footballapp.fotballapp.feature.favouriteteam

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.anggitprayogo.footballapp.fotballapp.R
import com.anggitprayogo.footballapp.fotballapp.adapter.FavouriteEventAdapter
import com.anggitprayogo.footballapp.fotballapp.adapter.FavouriteTeamAdapter
import com.anggitprayogo.footballapp.fotballapp.config.Config
import com.anggitprayogo.footballapp.fotballapp.database.database
import com.anggitprayogo.footballapp.fotballapp.feature.detaileventleague.DetailEventLeagueActivity
import com.anggitprayogo.footballapp.fotballapp.feature.detailteam.DetailTeamActivity
import com.anggitprayogo.footballapp.fotballapp.model.favourite.FavouriteTeam
import com.google.gson.GsonBuilder
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class FavouriteTeamFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var adapter: FavouriteTeamAdapter

    var datas: MutableList<FavouriteTeam> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_favourite_team, container, false)

        recyclerView = view.findViewById(R.id.rv_team)
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout)


        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        adapter = FavouriteTeamAdapter(datas, {
            startActivity<DetailTeamActivity>(
                    Config.LEAGUE_ID to it.teamId!!
            )
        })
        recyclerView.adapter = adapter


        getFavourite()

        // Inflate the layout for this fragment
        return view
    }

    private fun getFavourite() {
        try {

            context?.database?.use {
                val queryBuilder = select(FavouriteTeam.TABLE_NAME)
                val result = queryBuilder.parseList(classParser<FavouriteTeam>())
                datas.addAll(result)
                adapter.notifyDataSetChanged()
                Log.d("RESULT : ", GsonBuilder().setPrettyPrinting().create().toJson(datas))
            }

        }catch (e: SQLiteConstraintException){
            toast(e.localizedMessage)
        }
    }

}
