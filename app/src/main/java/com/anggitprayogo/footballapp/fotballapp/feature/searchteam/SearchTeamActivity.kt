package com.anggitprayogo.footballapp.fotballapp.feature.searchteam

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.View
import android.widget.ProgressBar
import com.anggitprayogo.footballapp.fotballapp.R
import com.anggitprayogo.footballapp.fotballapp.adapter.SearchTeamAdapter
import com.anggitprayogo.footballapp.fotballapp.config.Config
import com.anggitprayogo.footballapp.fotballapp.feature.detailteam.DetailTeamActivity
import com.anggitprayogo.footballapp.fotballapp.model.teams.Team
import com.anggitprayogo.footballapp.fotballapp.model.teams.TeamResponse
import com.anggitprayogo.footballapp.fotballapp.network.repository.MatchRepository
import kotlinx.android.synthetic.main.activity_search_team.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.startActivity

class SearchTeamActivity : AppCompatActivity(), SearchTeamView{

    var load: ProgressBar? = null
    var present: SearchTeamPresenter? = null
    var adapterList: SearchTeamAdapter? = null
    var teams: MutableList<Team> = mutableListOf()
    var hasil: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_team)

        rv_list_match.layoutManager = LinearLayoutManager(this)

        present = SearchTeamPresenter(this, MatchRepository())


        search_match.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }
            override fun onQueryTextChange(query: String?): Boolean {
                progressBarId.visibility = View.VISIBLE
                hasil = query!!
                if (query.length > 2) {
                    //present?.getMatchLast(hasil!!)
                    present?.getTeamsSearch(hasil!!)
                    progressBarId.visibility = View.INVISIBLE
                    adapterList = SearchTeamAdapter(teams) {
                        startActivity<DetailTeamActivity>(
                                Config.LEAGUE_ID to "${it.idTeam}"
                        )
                    }
                    rv_list_match.adapter = adapterList
                }
                return true
            }
        })
    }

    override fun showLoadingProgress() {
        load?.visibility = View.VISIBLE
    }

    override fun hideLoadingProgress() {
        load?.visibility = View.GONE
    }

    override fun showEventList(data: List<Team>?) {
        teams.clear()
        if (data != null) {
            teams.addAll(data)
        }
        adapterList?.notifyDataSetChanged()
    }

    override fun onDataLoaded(data: TeamResponse?) {
    }

    override fun onDataError() {
    }

}
