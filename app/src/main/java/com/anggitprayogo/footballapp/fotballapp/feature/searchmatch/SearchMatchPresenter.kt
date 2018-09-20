package com.anggitprayogo.footballapp.fotballapp.feature.searchmatch

import android.util.Log
import com.anggitprayogo.footballapp.fotballapp.model.searchevent.SearchEventResponse
import com.anggitprayogo.footballapp.fotballapp.network.repository.MatchRepository
import com.anggitprayogo.footballapp.fotballapp.network.repository.SearchMatchCallback
import com.google.gson.GsonBuilder
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class SearchMatchPresenter(val view  : SearchMatchView,
                           val repository: MatchRepository) {

    fun getMatchSearch(id: String) {
        view.showLoadingProgress()
        repository.getSearchMatch(id, object : SearchMatchCallback<SearchEventResponse?> {
            override fun onDataLoaded(data: SearchEventResponse?) {
                view.hideLoadingProgress()
                Log.e("RESULT : ",GsonBuilder().setPrettyPrinting().create().toJson(data!!.event))
                async(UI){

                    val dataMatch = bg { data?.event }
                    Log.e("data","coba 2"+dataMatch)
                    view.hideLoadingProgress()
                    view.showEventList(dataMatch.await())
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