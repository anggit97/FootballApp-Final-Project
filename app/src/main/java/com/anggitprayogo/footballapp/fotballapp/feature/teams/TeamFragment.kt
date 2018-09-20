package com.anggitprayogo.footballapp.fotballapp.feature.teams

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import com.anggitprayogo.footballapp.fotballapp.R
import com.anggitprayogo.footballapp.fotballapp.adapter.TeamAdapter
import com.anggitprayogo.footballapp.fotballapp.config.Config
import com.anggitprayogo.footballapp.fotballapp.feature.detailteam.DetailTeamActivity
import com.anggitprayogo.footballapp.fotballapp.feature.searchteam.SearchTeamActivity
import com.anggitprayogo.footballapp.fotballapp.model.teams.Team
import com.anggitprayogo.footballapp.fotballapp.model.teams.TeamResponse
import com.anggitprayogo.footballapp.fotballapp.network.repository.MatchRepository
import com.arlib.floatingsearchview.FloatingSearchView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity

class TeamFragment : Fragment(), TeamView{

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var spinner: Spinner
    private lateinit var presenter: TeamPresenter
    private lateinit var adapter: TeamAdapter
    private lateinit var floatSearchView: FloatingSearchView
    private lateinit var ivSearch: ImageView

    var datas: MutableList<Team> = mutableListOf()
    var leagueName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_team, container, false)

//        leagueName = resources.getStringArray(R.array.league).get(0)
        leagueName = "English%20Premier%20League"

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout)
        recyclerView = view.findViewById(R.id.rv_team)
        spinner = view.findViewById(R.id.spn_league)
        floatSearchView = view.findViewById(R.id.floating_search_view)
        ivSearch = view.findViewById(R.id.iv_search)

        presenter = TeamPresenter(this, MatchRepository())


        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))
        adapter = TeamAdapter(datas) {
            //Toast.makeText(activity, it.strTeam, Toast.LENGTH_SHORT).show()
            startActivity<DetailTeamActivity>(
                    Config.LEAGUE_ID to it.idTeam
            )
        }
        recyclerView.adapter = adapter


        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        presenter.getTeams(leagueName)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                leagueName = spinner.selectedItem.toString()
                presenter.getTeams(leagueName)

            }

        }

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            presenter.getTeams(leagueName)
        }


        floatSearchView.setOnQueryChangeListener { oldQuery, newQuery ->
            Log.d("QUERY : ", "Old Query : ${oldQuery}, New Query : ${newQuery}, COUNT : ${adapter.itemCount}")
            adapter.filter.filter(newQuery)
        }


        ivSearch.setOnClickListener {
            startActivity<SearchTeamActivity>()
        }

        // Inflate the layout for this fragment
        return view
    }

    override fun showLoading() {
        swipeRefreshLayout.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showTeamResponse(data: List<Team>) {
        Log.d("DATA", ""+data.size)
        datas.clear()
        datas.addAll(data)
        adapter.notifyDataSetChanged()
        Log.d("DATA", ""+datas.size)
    }

    override fun showTeamError() {

        Toast.makeText(activity, "Gagal fetch data team error", Toast.LENGTH_LONG).show()

    }

    override fun onDataLoaded(data: TeamResponse) {
    }

    override fun onDataError() {
        Toast.makeText(activity, "Gagal fetch data team", Toast.LENGTH_LONG).show()
    }


}
