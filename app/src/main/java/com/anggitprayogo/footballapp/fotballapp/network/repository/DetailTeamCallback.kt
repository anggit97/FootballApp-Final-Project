package com.anggitprayogo.footballapp.fotballapp.network.repository

import com.anggitprayogo.footballapp.fotballapp.model.detailteams.DetailTeamResponse

interface DetailTeamCallback<T> {

    fun onDataLoaded(data: DetailTeamResponse)

    fun onDataError()

}