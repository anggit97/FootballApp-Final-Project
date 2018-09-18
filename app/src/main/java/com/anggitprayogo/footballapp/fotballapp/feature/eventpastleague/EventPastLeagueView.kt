package com.anggitprayogo.footballapp.fotballapp.feature.eventpastleague

import com.anggitprayogo.footballapp.fotballapp.model.eventspastleague.EventPast
import com.anggitprayogo.footballapp.fotballapp.model.eventspastleague.EventPastLeagueResponse
import com.anggitprayogo.footballapp.fotballapp.network.repository.EventPastLeagueCallback

interface EventPastLeagueView: EventPastLeagueCallback<EventPastLeagueResponse>{

    fun showLoading()

    fun hideProgress()

    fun showToast(message: String?)

    fun showEventList(data: List<EventPast>)

    fun showDataLoaded()

    fun showEventError()

}