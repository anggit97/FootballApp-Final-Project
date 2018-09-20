package com.anggitprayogo.footballapp.fotballapp.feature.searchteam

import android.util.Log
import com.anggitprayogo.footballapp.fotballapp.model.teams.Team
import com.anggitprayogo.footballapp.fotballapp.model.teams.TeamResponse
import com.anggitprayogo.footballapp.fotballapp.network.repository.MatchRepository
import com.anggitprayogo.footballapp.fotballapp.network.repository.SearchTeamCallback
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class SearchTeamPresenter(val view: SearchTeamView,
                          val repository: MatchRepository) {

    fun getTeamsSearch(id: String) {
        view.showLoadingProgress()
        repository.getSearchTeams(id, object : SearchTeamCallback<TeamResponse?> {
            override fun onDataLoaded(data: TeamResponse?) {
                view.onDataLoaded(data)

                async(UI){

                    val dataMatch = bg { data?.teams }
                    Log.e("data","coba teams : "+dataMatch)
                    //view.showEventList(dataMatch.await())
                    view.hideLoadingProgress()
                    view.showEventList(dataMatch.await() as List<Team>?)
                }

            }

            override fun onDataError() {
                view.onDataError()
                view.hideLoadingProgress()
            }
        })
        view.hideLoadingProgress()
    }
}