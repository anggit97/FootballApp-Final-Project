package com.anggitprayogo.footballapp.fotballapp.feature.detaileventleague

import com.anggitprayogo.footballapp.fotballapp.model.detailevents.DetailEvent
import com.anggitprayogo.footballapp.fotballapp.model.detailevents.DetailEventLeagueResponse
import com.anggitprayogo.footballapp.fotballapp.model.detailteams.DetailTeam
import com.anggitprayogo.footballapp.fotballapp.model.teams.Team
import com.anggitprayogo.footballapp.fotballapp.network.repository.DetailEventLeagueCallback

interface DetailEventLeagueView: DetailEventLeagueCallback<DetailEventLeagueResponse> {

    fun showLoading()

    fun hideLoading()

    fun showDetailEventResponse(datas: List<DetailEvent>)

    fun showDetailEventError()

    fun showDetailHomeTeamResponse(data: List<DetailTeam>)

    fun showDetailHomeTeamError()

    fun showDetailAwayTeamResponse(data: List<DetailTeam>)

    fun showDetailAwayTeamError()

}