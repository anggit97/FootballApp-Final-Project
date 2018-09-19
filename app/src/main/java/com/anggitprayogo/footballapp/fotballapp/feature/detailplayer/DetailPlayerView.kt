package com.anggitprayogo.footballapp.fotballapp.feature.detailplayer

import com.anggitprayogo.footballapp.fotballapp.model.player.PlayerResponse
import com.anggitprayogo.footballapp.fotballapp.network.repository.PlayerCallback
import com.kotlinje.submit2.model.detail_player.PlayersItemDetail
import com.kotlinje.submit2.model.detail_player.ResponseDetailPlayer

interface DetailPlayerView: PlayerCallback<ResponseDetailPlayer> {

    fun showLoading()

    fun hideLoading()

    fun showPlayerResponse(data: List<PlayersItemDetail?>?)

    fun showPlayerError()

}