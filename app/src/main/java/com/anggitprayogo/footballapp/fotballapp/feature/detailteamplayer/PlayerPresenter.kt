package com.anggitprayogo.footballapp.fotballapp.feature.detailteamplayer

import com.anggitprayogo.footballapp.fotballapp.model.player.PlayerResponse
import com.anggitprayogo.footballapp.fotballapp.network.repository.MatchRepository
import com.anggitprayogo.footballapp.fotballapp.network.repository.PlayerCallback
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PlayerPresenter(val view: PlayerView,
                      val repository: MatchRepository) {

    fun getPlayer(id: String){
        view.showLoading()
        repository.getPlayerTeam(id, object : PlayerCallback<PlayerResponse>{
            override fun onDataLoaded(data: PlayerResponse) {
                view.hideLoading()
                async(UI){
                    val dataPlayer = bg { data.player }
                    view.showPlayerResponse(dataPlayer.await())
                }
            }

            override fun onDataError() {
                view.hideLoading()
                view.showPlayerError()
            }

        })
    }

}