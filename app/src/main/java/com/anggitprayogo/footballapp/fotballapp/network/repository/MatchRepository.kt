package com.anggitprayogo.footballapp.fotballapp.network.repository

import com.anggitprayogo.footballapp.fotballapp.model.eventsnextleague.EventNextLeagueResponse
import com.anggitprayogo.footballapp.fotballapp.model.eventspastleague.EventPastLeagueResponse
import com.anggitprayogo.footballapp.fotballapp.network.retrofit.RetrofitEndpoint
import com.anggitprayogo.footballapp.fotballapp.network.retrofit.RetrofitService
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

//    fun getNextMatch(id: String, callback: EventNextLeagueResponse<EventNextLeagueResponse>){
//
//        RetrofitService.createService(RetrofitEndpoint::class.java)
//                .getPastMatch(id)
//                .enqueue(object : retrofit2.Callback<EventPastLeagueResponse>{
//
//                    override fun onFailure(call: Call<EventPastLeagueResponse>?, t: Throwable?) {
//                        callback.onDataError()
//                    }
//
//                    override fun onResponse(call: Call<EventPastLeagueResponse>?, response: Response<EventPastLeagueResponse>?) {
//
//                        if (response!!.isSuccessful){
//                            callback.onDataLoaded(response.body()!!)
//                        }else{
//                            callback.onDataError()
//                        }
//
//                    }
//
//                })
//
//    }

}