package com.anggitprayogo.footballapp.fotballapp.network.repository

import android.util.Log
import com.anggitprayogo.footballapp.fotballapp.model.eventsnextleague.EventNextLeagueResponse
import com.anggitprayogo.footballapp.fotballapp.model.eventspastleague.EventPastLeagueResponse
import com.anggitprayogo.footballapp.fotballapp.model.teams.TeamResponse
import com.anggitprayogo.footballapp.fotballapp.network.retrofit.RetrofitEndpoint
import com.anggitprayogo.footballapp.fotballapp.network.retrofit.RetrofitService
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Response

class MatchRepository {

    fun getPastMatch(id: String, callback: EventPastLeagueCallback<EventPastLeagueResponse>){

        RetrofitService.createService(RetrofitEndpoint::class.java)
                .getPastMatch(id)
                .enqueue(object : retrofit2.Callback<EventPastLeagueResponse>{

                    override fun onFailure(call: Call<EventPastLeagueResponse>?, t: Throwable?) {
                        callback.onDataError()
                    }

                    override fun onResponse(call: Call<EventPastLeagueResponse>?, response: Response<EventPastLeagueResponse>?) {

                        if (response!!.isSuccessful){
                            callback.onDataLoaded(response.body()!!)
                        }else{
                            callback.onDataError()
                        }

                    }

                })

    }

    fun getNextMatch(id: String, callback: EventNextLeagueCallback<EventNextLeagueResponse>){

        RetrofitService.createService(RetrofitEndpoint::class.java)
                .getNextMatch(id)
                .enqueue(object : retrofit2.Callback<EventNextLeagueResponse>{
                    override fun onFailure(call: Call<EventNextLeagueResponse>?, t: Throwable?) {
                        callback.onDataError()
                    }

                    override fun onResponse(call: Call<EventNextLeagueResponse>?, response: Response<EventNextLeagueResponse>?) {
                        if (response!!.isSuccessful){
                            callback.onDataLoaded(response.body()!!)
                        }else{
                            callback.onDataError()
                        }
                    }

                })
    }


    fun getTeams(id: String, callback: TeamCallback<TeamResponse>){

        RetrofitService.createService(RetrofitEndpoint::class.java)
                .getTeams(id)
                .enqueue(object: retrofit2.Callback<TeamResponse>{
                    override fun onFailure(call: Call<TeamResponse>?, t: Throwable?) {
                        callback.onDataError()
                    }

                    override fun onResponse(call: Call<TeamResponse>?, response: Response<TeamResponse>?) {

                        if (response!!.isSuccessful){
                            Log.d("TEAMS", GsonBuilder().setPrettyPrinting().create().toJson(response.body()))
                            callback.onDataLoaded(response.body()!!)
                        }else{
                            Log.d("GAGAL", "GAGAL")
                            callback.onDataError()
                        }

                    }

                })
    }


}