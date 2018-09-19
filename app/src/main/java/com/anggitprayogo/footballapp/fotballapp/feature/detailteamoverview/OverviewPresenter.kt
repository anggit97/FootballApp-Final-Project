package com.anggitprayogo.footballapp.fotballapp.feature.detailteamoverview

import com.anggitprayogo.footballapp.fotballapp.model.detailteams.DetailTeamResponse
import com.anggitprayogo.footballapp.fotballapp.model.teams.TeamResponse
import com.anggitprayogo.footballapp.fotballapp.network.repository.DetailTeamCallback
import com.anggitprayogo.footballapp.fotballapp.network.repository.MatchRepository
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class OverviewPresenter(val view: OverviewView,
                        val repository: MatchRepository) {

    fun getDetailTeam(id: String){
        view.showLoading()
        repository.getDetailTeam(id, object: DetailTeamCallback<DetailTeamResponse>{
            override fun onDataLoaded(data: DetailTeamResponse) {
               view.hideLoading()

                async(UI){
                    val dataDetailItem = bg { data.teams }
                    view.showDetailTeamResponse(dataDetailItem.await())
                }
            }

            override fun onDataError() {
                view.hideLoading()
                view.showDetailTeamError()
            }

        })
    }

}