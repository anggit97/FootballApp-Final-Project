package com.anggitprayogo.footballapp.fotballapp.network.repository

import com.anggitprayogo.footballapp.fotballapp.model.searchevent.SearchEventResponse

interface SearchMatchCallback<T> {

    fun onDataLoaded(data: SearchEventResponse?)

    fun onDataError()
}