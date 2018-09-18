package com.anggitprayogo.footballapp.fotballapp.network.repository

import com.anggitprayogo.footballapp.fotballapp.model.teams.TeamResponse

interface TeamCallback<T> {

    fun onDataLoaded(data: TeamResponse)

    fun onDataError()

}