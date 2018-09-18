package com.anggitprayogo.footballapp.fotballapp.feature.eventnextleague

import android.util.Log
import com.anggitprayogo.footballapp.fotballapp.feature.eventpastleague.EventPastLeagueView
import com.anggitprayogo.footballapp.fotballapp.model.eventsnextleague.EventNextLeagueResponse
import com.anggitprayogo.footballapp.fotballapp.model.eventspastleague.EventPastLeagueResponse
import com.anggitprayogo.footballapp.fotballapp.network.repository.EventNextLeagueCallback
import com.anggitprayogo.footballapp.fotballapp.network.repository.EventPastLeagueCallback
import com.anggitprayogo.footballapp.fotballapp.network.repository.MatchRepository
import com.google.gson.GsonBuilder
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class EventNextLeaguePresenter(val view: EventNextLeagueView,
                               val matchRepository: MatchRepository) {

    val TAG = javaClass.simpleName

    fun getNextEvent(id: String){
        view.showLoading()
        matchRepository.getNextMatch(id, object : EventNextLeagueCallback<EventNextLeagueResponse> {
            override fun onDataLoaded(data: EventNextLeagueResponse) {
                view.hideLoading()
                view.showEventError()

                async(UI){
                    Log.d(TAG, GsonBuilder().setPrettyPrinting().create().toJson(data.events))
                    val dataEvent = bg { data?.events }
                    view.showEventList(dataEvent.await())
                }
            }

            override fun onDataError() {
                view.hideLoading()
                view.showEventError()
            }

        })
    }

}