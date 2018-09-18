package com.anggitprayogo.footballapp.fotballapp.network.repository

import com.anggitprayogo.footballapp.fotballapp.model.eventspastleague.EventPastLeagueResponse

interface EventPastLeagueCallback<T> {

    fun onDataLoaded(data: EventPastLeagueResponse)

    fun onDataError()

}