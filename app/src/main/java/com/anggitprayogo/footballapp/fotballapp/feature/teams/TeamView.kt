package com.anggitprayogo.footballapp.fotballapp.feature.teams

import com.anggitprayogo.footballapp.fotballapp.model.teams.Team
import com.anggitprayogo.footballapp.fotballapp.model.teams.TeamResponse
import com.anggitprayogo.footballapp.fotballapp.network.repository.TeamCallback

interface TeamView: TeamCallback<TeamResponse>{

    fun showLoading()

    fun hideLoading()

    fun showTeamResponse(data: List<Team>)

    fun showTeamError()

}