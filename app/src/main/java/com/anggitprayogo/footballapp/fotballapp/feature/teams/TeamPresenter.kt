package com.anggitprayogo.footballapp.fotballapp.feature.teams

import android.util.Log
import com.anggitprayogo.footballapp.fotballapp.model.teams.Team
import com.anggitprayogo.footballapp.fotballapp.model.teams.TeamResponse
import com.anggitprayogo.footballapp.fotballapp.network.repository.MatchRepository
import com.anggitprayogo.footballapp.fotballapp.network.repository.TeamCallback
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class TeamPresenter(val view: TeamView,
                    val repository: MatchRepository) {

    fun getTeams(id: String){
        view.showLoading()
        repository.getTeams(id, object : TeamCallback<TeamResponse>{
            override fun onDataLoaded(data: TeamResponse) {
                view.hideLoading()

                async(UI){
                    val dataTeam = bg { data?.teams }
                    view.showTeamResponse(dataTeam.await())
                }
            }

            override fun onDataError() {
                view.hideLoading()
                view.showTeamError()
            }

        })

    }
}