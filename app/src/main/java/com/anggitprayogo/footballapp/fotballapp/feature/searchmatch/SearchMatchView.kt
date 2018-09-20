package com.anggitprayogo.footballapp.fotballapp.feature.searchmatch

import com.anggitprayogo.footballapp.fotballapp.model.eventsnextleague.EventNext
import com.anggitprayogo.footballapp.fotballapp.model.eventsnextleague.EventNextLeagueResponse
import com.anggitprayogo.footballapp.fotballapp.model.searchevent.SearchEvent
import com.anggitprayogo.footballapp.fotballapp.model.searchevent.SearchEventResponse
import com.anggitprayogo.footballapp.fotballapp.network.repository.SearchMatchCallback

interface SearchMatchView: SearchMatchCallback<SearchEventResponse> {

    fun showLoadingProgress()
    fun hideLoadingProgress()
    fun showToast(message: String?)
    fun showEventList(data:List<SearchEvent>?)
}