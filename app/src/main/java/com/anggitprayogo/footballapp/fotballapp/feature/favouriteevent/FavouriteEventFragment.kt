package com.anggitprayogo.footballapp.fotballapp.feature.favouriteevent

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
import com.anggitprayogo.footballapp.fotballapp.config.Config
import com.anggitprayogo.footballapp.fotballapp.database.database
import com.anggitprayogo.footballapp.fotballapp.feature.detaileventleague.DetailEventLeagueActivity
import com.anggitprayogo.footballapp.fotballapp.model.favourite.FavouriteEvent
import com.google.gson.GsonBuilder
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.rowParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class FavouriteEventFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var adapter: FavouriteEventAdapter

    var datas: MutableList<FavouriteEvent> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_favourite_event, container, false)

        recyclerView = view.findViewById(R.id.rv_league)
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout)


        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        adapter = FavouriteEventAdapter(datas, {
            Log.d(
                    "HASIL : ",
                    "Nama Event : ${it.eventName}, Date : ${it.strDate}, Home Name : ${it.strHomeName}, " +
                            "Home Score : ${it.strHomeScore}, AwayScore : ${it.strAwayScore}, Away Name : ${it.strAwayScore}, " +
                            "TIME : ${it.strTime}"
            )
            startActivity<DetailEventLeagueActivity>(
                    Config.LEAGUE_ID to it.eventId!!
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
                val queryBuilder = select(FavouriteEvent.TABLE_FAVOURITE_EVENT)
                val result = queryBuilder.parseList(classParser<FavouriteEvent>())
//                val result = queryBuilder.parseList(parser = rowParser{
//                    id: Long?, evenId: String?, evenTgl: String?, eventTime: String?,
//                    homeName: String?, awayName: String?,
//                    scoreHome: String?, scoreAway: String?,
//                    eventName: String? ->
//                    FavouriteEvent(id, evenId, eventName, homeName, awayName, scoreHome, scoreAway, evenTgl, eventTime)
//                })
                datas.addAll(result)
                adapter.notifyDataSetChanged()
                Log.d("RESULT : ", GsonBuilder().setPrettyPrinting().create().toJson(datas))
            }

        }catch (e: SQLiteConstraintException){
            toast(e.localizedMessage)
        }
    }


}
