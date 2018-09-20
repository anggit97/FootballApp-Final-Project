package com.anggitprayogo.footballapp.fotballapp.feature.searchmatch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import com.anggitprayogo.footballapp.fotballapp.R
import com.anggitprayogo.footballapp.fotballapp.adapter.SearchMatchAdapter
import com.anggitprayogo.footballapp.fotballapp.config.Config
import com.anggitprayogo.footballapp.fotballapp.feature.detaileventleague.DetailEventLeagueActivity
import com.anggitprayogo.footballapp.fotballapp.model.searchevent.SearchEvent
import com.anggitprayogo.footballapp.fotballapp.model.searchevent.SearchEventResponse
import com.anggitprayogo.footballapp.fotballapp.network.repository.MatchRepository
import kotlinx.android.synthetic.main.activity_search_match.*
import org.jetbrains.anko.startActivity

class SearchMatchActivity : AppCompatActivity(), SearchMatchView {


    var present: SearchMatchPresenter? = null
    var adapterList: SearchMatchAdapter? = null
    var liga: MutableList<SearchEvent> = mutableListOf()
    var hasil: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_match)

        setSupportActionBar(toolbar)
        rv_list_match.layoutManager = LinearLayoutManager(this)

        present = SearchMatchPresenter(this, MatchRepository())


        search_match.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                progressBarId.visibility = View.VISIBLE
                hasil = query!!

                if (query.length > 2) {
                    present?.getMatchSearch(hasil!!)
                    Log.d("ET",query.toString())
                    progressBarId.visibility = View.INVISIBLE
                    adapterList = SearchMatchAdapter(liga) {
                        startActivity<DetailEventLeagueActivity>(
                                Config.LEAGUE_ID to it.idEvent
                        )
                    }
                    rv_list_match.adapter = adapterList
                }

                return true
            }
        })
    }

    override fun showLoadingProgress() {
        progressBarId.visibility = View.VISIBLE
    }

    override fun hideLoadingProgress() {
        progressBarId.visibility = View.GONE
    }

    override fun showToast(message: String?) {
    }

    override fun showEventList(data: List<com.anggitprayogo.footballapp.fotballapp.model.searchevent.SearchEvent>?) {
        liga.clear()
        Log.d("DATA DATA: ",""+data?.size)
        if (data != null) {
            liga.addAll(data)

        }
        Log.d("DATA LIGA: ",""+liga?.size)
        adapterList?.notifyDataSetChanged()
    }

    override fun onDataLoaded(data: SearchEventResponse?) {
        Log.d("DATA LOADED: ",""+data?.event?.size)
    }

    override fun onDataError() {
        Log.d("DATA ERROR: ","")
    }
}
