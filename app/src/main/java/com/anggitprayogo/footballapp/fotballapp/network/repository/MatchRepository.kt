package com.anggitprayogo.footballapp.fotballapp.network.repository

import android.util.Log
import com.anggitprayogo.footballapp.fotballapp.model.detailevents.DetailEventLeagueResponse
import com.anggitprayogo.footballapp.fotballapp.model.detailteams.DetailTeamResponse
import com.anggitprayogo.footballapp.fotballapp.model.eventsnextleague.EventNextLeagueResponse
import com.anggitprayogo.footballapp.fotballapp.model.eventspastleague.EventPastLeagueResponse
import com.anggitprayogo.footballapp.fotballapp.model.player.PlayerResponse
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

    fun getDetailMatch(id: String, callback: DetailEventLeagueCallback<DetailEventLeagueResponse>){

        RetrofitService.createService(RetrofitEndpoint::class.java)
                .getDetailMatch(id)
                .enqueue(object : retrofit2.Callback<DetailEventLeagueResponse>{
                    override fun onFailure(call: Call<DetailEventLeagueResponse>?, t: Throwable?) {
                        Log.d("FAILURE ", t?.message)
                        callback.onDataError()

                    }

                    override fun onResponse(call: Call<DetailEventLeagueResponse>?, response: Response<DetailEventLeagueResponse>?) {
                        if (response!!.isSuccessful) {
                            Log.d("DETAIL MATCH ", GsonBuilder().setPrettyPrinting().create().toJson(response.body()))

                            callback.onDataLoaded(response.body()!!)

                        }else{

                            Log.d("FAILURE GAGAL", "GAGAL")
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
                        Log.d("FAILURE ", t?.message)
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

    fun getDetailTeam(id: String, callback: DetailTeamCallback<DetailTeamResponse>){

        RetrofitService.createService(RetrofitEndpoint::class.java)
                .getDetailTeam(id)
                .enqueue(object : retrofit2.Callback<DetailTeamResponse>{
                    override fun onFailure(call: Call<DetailTeamResponse>?, t: Throwable?) {
                        callback.onDataError()
                    }

                    override fun onResponse(call: Call<DetailTeamResponse>?, response: Response<DetailTeamResponse>?) {
                        if (response!!.isSuccessful){
                            Log.d("DETAIL TEAM ", GsonBuilder().setPrettyPrinting().create().toJson(response.body()))
                            callback.onDataLoaded(response.body()!!)
                        }else{
                            callback.onDataError()
                        }
                    }

                })

    }


    fun getPlayerTeam(id: String, callback: PlayerCallback<PlayerResponse>){

        RetrofitService.createService(RetrofitEndpoint::class.java)
                .getTeamPlayer(id)
                .enqueue(object: retrofit2.Callback<PlayerResponse>{
                    override fun onFailure(call: Call<PlayerResponse>?, t: Throwable?) {
                        callback.onDataError()
                    }

                    override fun onResponse(call: Call<PlayerResponse>?, response: Response<PlayerResponse>?) {
                        if (response!!.isSuccessful){
                            Log.d("PLAYER : ", GsonBuilder().setPrettyPrinting().create().toJson(response.body()))
                            callback.onDataLoaded(response.body()!!)
                        }else{
                            callback.onDataError()
                        }
                    }

                })
    }

}