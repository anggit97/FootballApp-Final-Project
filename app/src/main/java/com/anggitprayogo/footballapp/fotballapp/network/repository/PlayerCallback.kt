package com.anggitprayogo.footballapp.fotballapp.network.repository

import com.anggitprayogo.footballapp.fotballapp.model.player.PlayerResponse

interface PlayerCallback<T> {

    fun onDataLoaded(data: PlayerResponse)

    fun onDataError()

}