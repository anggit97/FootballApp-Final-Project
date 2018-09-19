package com.anggitprayogo.footballapp.fotballapp.feature.detailplayer

import com.anggitprayogo.footballapp.fotballapp.network.repository.DetailPlayerCallback
import com.anggitprayogo.footballapp.fotballapp.network.repository.MatchRepository
import com.kotlinje.submit2.model.detail_player.ResponseDetailPlayer
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DetailPlayerPresenter(val view: DetailPlayerView,
                            val repository: MatchRepository) {

    fun getDetailPlayer(id: String){
        view.showLoading()
        repository.getDetailPlaye(id, object: DetailPlayerCallback<ResponseDetailPlayer>{
            override fun onDataLoaded(data: ResponseDetailPlayer) {
                view.hideLoading()
                async(UI){
                    val dataDetailPlayer = bg { data.players }
                    view.showPlayerResponse(dataDetailPlayer.await())
                }
            }

            override fun onDataError() {
                view.hideLoading()
            }

        })
    }

}