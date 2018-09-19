package com.anggitprayogo.footballapp.fotballapp.feature.detailteam

import com.anggitprayogo.footballapp.fotballapp.model.detailteams.DetailTeam
import com.anggitprayogo.footballapp.fotballapp.model.teams.Team
import com.anggitprayogo.footballapp.fotballapp.model.teams.TeamResponse
import com.anggitprayogo.footballapp.fotballapp.network.repository.DetailTeamCallback

interface DetailTeamView: DetailTeamCallback<TeamResponse>{

    fun showLoading()

    fun hideLoading()

    fun showDetailTeamResponse(data: List<DetailTeam>)

    fun showDetailTeamError()

}