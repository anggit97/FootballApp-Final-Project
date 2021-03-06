package com.anggitprayogo.footballapp.fotballapp.network.repository

import com.anggitprayogo.footballapp.fotballapp.model.teams.TeamResponse

interface SearchTeamCallback<T> {

    fun onDataLoaded(data: TeamResponse?)
    fun onDataError()
}