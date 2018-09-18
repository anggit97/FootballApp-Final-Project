package com.anggitprayogo.footballapp.fotballapp.feature.eventpastleague

import android.util.Log
import com.anggitprayogo.footballapp.fotballapp.model.eventspastleague.EventPastLeagueResponse
import com.anggitprayogo.footballapp.fotballapp.network.repository.EventPastLeagueCallback
import com.anggitprayogo.footballapp.fotballapp.network.repository.MatchRepository
import com.google.gson.GsonBuilder
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class EventPastLeaguePresenter(val view: EventPastLeagueView,
                               val matchRepository: MatchRepository) {

    val TAG = javaClass.simpleName

    fun getPastEvent(id: String){
        view.showLoading()
        matchRepository.getPastMatch(id, object : EventPastLeagueCallback<EventPastLeagueResponse>{
            override fun onDataLoaded(data: EventPastLeagueResponse) {
                view.hideProgress()
                view.showDataLoaded()

                async(UI){
                    Log.d(TAG, GsonBuilder().setPrettyPrinting().create().toJson(data.events))
                    val dataEvent = bg { data?.events }
                    view.showEventList(dataEvent.await())
                }
            }

            override fun onDataError() {
                view.hideProgress()
                view.showEventError()
            }

        })
    }

}