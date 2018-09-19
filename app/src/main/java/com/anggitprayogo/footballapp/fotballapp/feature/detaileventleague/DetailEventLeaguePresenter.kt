package com.anggitprayogo.footballapp.fotballapp.feature.detaileventleague

import com.anggitprayogo.footballapp.fotballapp.model.detailevents.DetailEventLeagueResponse
import com.anggitprayogo.footballapp.fotballapp.model.detailteams.DetailTeamResponse
import com.anggitprayogo.footballapp.fotballapp.network.repository.DetailEventLeagueCallback
import com.anggitprayogo.footballapp.fotballapp.network.repository.DetailTeamCallback
import com.anggitprayogo.footballapp.fotballapp.network.repository.MatchRepository
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DetailEventLeaguePresenter(val view: DetailEventLeagueView,
                                 val repository: MatchRepository) {

    fun getDetailMatch(id: String){
        view.showLoading()
        repository.getDetailMatch(id, object : DetailEventLeagueCallback<DetailEventLeagueResponse>{
            override fun onDataLoaded(data: DetailEventLeagueResponse) {
                view.hideLoading()

                async(UI){
                    val dataDetailMatch = bg { data.events }
                    view.showDetailEventResponse(dataDetailMatch.await())
                }

            }

            override fun onDataError() {
                view.hideLoading()
                view.showDetailEventError()
            }

        })
    }

    fun getDetailHomeTeam(idHomeTeam: String) {
        view.showLoading()
        repository.getDetailTeam(idHomeTeam, object : DetailTeamCallback<DetailTeamResponse>{
            override fun onDataLoaded(data: DetailTeamResponse) {
                view.hideLoading()
                async(UI){
                    val dataDetailTeam = bg { data.teams }
                    view.showDetailHomeTeamResponse(dataDetailTeam.await())
                }
            }

            override fun onDataError() {
                view.hideLoading()
                view.showDetailHomeTeamError()
            }

        })
    }

    fun getDetailAwayTeam(idAwayTeam: String) {
        view.showLoading()
        repository.getDetailTeam(idAwayTeam, object : DetailTeamCallback<DetailTeamResponse>{
            override fun onDataLoaded(data: DetailTeamResponse) {
                view.hideLoading()
                async(UI){
                    val dataDetailTeam = bg { data.teams }
                    view.showDetailAwayTeamResponse(dataDetailTeam.await())
                }
            }

            override fun onDataError() {
                view.hideLoading()
                view.showDetailAwayTeamError()
            }

        })
    }

}