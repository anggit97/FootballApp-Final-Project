package com.anggitprayogo.footballapp.fotballapp.feature.searchteam

import com.anggitprayogo.footballapp.fotballapp.model.teams.Team
import com.anggitprayogo.footballapp.fotballapp.model.teams.TeamResponse
import com.anggitprayogo.footballapp.fotballapp.network.repository.SearchTeamCallback

interface SearchTeamView: SearchTeamCallback<TeamResponse> {

    fun showLoadingProgress()
    fun hideLoadingProgress()
    fun showEventList(data:List<Team>?)

}