package com.anggitprayogo.footballapp.fotballapp.feature.detailteamplayer

import com.anggitprayogo.footballapp.fotballapp.model.player.Player
import com.anggitprayogo.footballapp.fotballapp.model.player.PlayerResponse
import com.anggitprayogo.footballapp.fotballapp.network.repository.PlayerCallback

interface PlayerView: PlayerCallback<PlayerResponse>{

    fun showLoading()

    fun hideLoading()

    fun showPlayerResponse(data: List<Player>)

    fun showPlayerError()

}