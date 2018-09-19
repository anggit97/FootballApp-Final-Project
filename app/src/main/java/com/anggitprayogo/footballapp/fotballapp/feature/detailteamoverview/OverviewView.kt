package com.anggitprayogo.footballapp.fotballapp.feature.detailteamoverview

import com.anggitprayogo.footballapp.fotballapp.model.detailteams.DetailTeam
import com.anggitprayogo.footballapp.fotballapp.model.detailteams.DetailTeamResponse
import com.anggitprayogo.footballapp.fotballapp.model.teams.Team
import com.anggitprayogo.footballapp.fotballapp.model.teams.TeamResponse
import com.anggitprayogo.footballapp.fotballapp.network.repository.DetailTeamCallback

interface OverviewView: DetailTeamCallback<DetailTeamResponse>{

    fun showLoading()

    fun hideLoading()

    fun showDetailTeamResponse(data: List<DetailTeam>)

    fun showDetailTeamError()

}