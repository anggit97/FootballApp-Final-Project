package com.anggitprayogo.footballapp.fotballapp.network.repository

import com.anggitprayogo.footballapp.fotballapp.model.eventsnextleague.EventNextLeagueResponse

interface EventNextLeagueCallback<T> {

    fun onDataError()

    fun onDataLoaded(data: EventNextLeagueResponse)

}