package com.anggitprayogo.footballapp.fotballapp.network.repository

import com.anggitprayogo.footballapp.fotballapp.model.detailevents.DetailEvent
import com.anggitprayogo.footballapp.fotballapp.model.detailevents.DetailEventLeagueResponse

interface DetailEventLeagueCallback<T> {

    fun onDataLoaded(data: DetailEventLeagueResponse)

    fun onDataError()

}