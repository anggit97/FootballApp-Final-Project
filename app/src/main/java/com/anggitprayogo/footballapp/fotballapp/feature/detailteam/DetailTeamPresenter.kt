package com.anggitprayogo.footballapp.fotballapp.feature.detailteam

import com.anggitprayogo.footballapp.fotballapp.model.detailteams.DetailTeamResponse
import com.anggitprayogo.footballapp.fotballapp.model.teams.TeamResponse
import com.anggitprayogo.footballapp.fotballapp.network.repository.DetailTeamCallback
import com.anggitprayogo.footballapp.fotballapp.network.repository.MatchRepository
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DetailTeamPresenter(val view: DetailTeamView,
                          val repository: MatchRepository) {

    fun getDetailTeam(id: String){

        view.showLoading()

        repository.getDetailTeam(id, object : DetailTeamCallback<DetailTeamResponse>{

            override fun onDataLoaded(data: DetailTeamResponse) {
                view.hideLoading()
                async(UI){
                    val dataDetailTeam = bg { data.teams }
                    view.showDetailTeamResponse(dataDetailTeam.await())
                }

            }

            override fun onDataError() {
                view.hideLoading()
                view.showDetailTeamError()
            }

        })

    }

}