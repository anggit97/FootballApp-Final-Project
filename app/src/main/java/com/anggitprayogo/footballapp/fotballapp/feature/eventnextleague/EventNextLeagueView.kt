package com.anggitprayogo.footballapp.fotballapp.feature.eventnextleague

import com.anggitprayogo.footballapp.fotballapp.model.eventsnextleague.EventNext
import com.anggitprayogo.footballapp.fotballapp.model.eventsnextleague.EventNextLeagueResponse
import com.anggitprayogo.footballapp.fotballapp.network.repository.EventNextLeagueCallback

interface EventNextLeagueView: EventNextLeagueCallback<EventNextLeagueResponse> {

    fun showLoading()

    fun hideLoading()

    fun showEventList(data: List<EventNext>)

    fun showEventError()

}