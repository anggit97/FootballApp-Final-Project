package com.anggitprayogo.footballapp.fotballapp.network.repository

import com.kotlinje.submit2.model.detail_player.ResponseDetailPlayer

interface DetailPlayerCallback<T> {

    fun onDataLoaded(data: ResponseDetailPlayer)

    fun onDataError()
}