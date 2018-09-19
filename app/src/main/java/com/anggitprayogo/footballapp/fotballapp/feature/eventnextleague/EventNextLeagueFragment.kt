package com.anggitprayogo.footballapp.fotballapp.feature.eventnextleague

import android.content.Context
import android.net.Uri
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
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast

import com.anggitprayogo.footballapp.fotballapp.R
import com.anggitprayogo.footballapp.fotballapp.adapter.EventNextLeagueAdapter
import com.anggitprayogo.footballapp.fotballapp.adapter.EventPastLeagueAdapter
import com.anggitprayogo.footballapp.fotballapp.config.Config
import com.anggitprayogo.footballapp.fotballapp.feature.detaileventleague.DetailEventLeagueActivity
import com.anggitprayogo.footballapp.fotballapp.feature.eventpastleague.EventPastLeaguePresenter
import com.anggitprayogo.footballapp.fotballapp.model.eventsnextleague.EventNext
import com.anggitprayogo.footballapp.fotballapp.model.eventsnextleague.EventNextLeagueResponse
import com.anggitprayogo.footballapp.fotballapp.network.repository.MatchRepository
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity

class EventNextLeagueFragment : Fragment(), EventNextLeagueView {

    private lateinit var presenter: EventNextLeaguePresenter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var rvLeague: RecyclerView
    private lateinit var spinner: Spinner
    private lateinit var adapter: EventNextLeagueAdapter
    
    var datas: MutableList<EventNext> = mutableListOf()
    var leagueId = "4238"
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_event_next_league, container, false)

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout)
        rvLeague = view.findViewById(R.id.rv_league)
        spinner = view.findViewById(R.id.spn_league)

        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        presenter = EventNextLeaguePresenter(this, MatchRepository())


        val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvLeague.layoutManager = linearLayoutManager
        rvLeague.addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))
        adapter = EventNextLeagueAdapter(datas,{
            startActivity<DetailEventLeagueActivity>(
                    Config.LEAGUE_ID to it.idEvent
            )
        })
        rvLeague.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {


            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                leagueId = spinner.selectedItem.toString()

                if (position== 0){
                    leagueId ="4328"
                }
                else if (position==1){
                    leagueId="4329"
                } else if (position==2){
                    leagueId="4331"
                } else if (position==3){
                    leagueId="4332"
                } else if (position==4){
                    leagueId="4334"
                } else if (position==5){
                    leagueId="4335"
                }

                Log.e("Tag", "spinner :" + leagueId)
                presenter.getNextEvent(leagueId)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            presenter.getNextEvent(leagueId)
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

    override fun showEventList(data: List<EventNext>) {
        Log.d("DATA", ""+data.size)
        datas.clear()
        datas.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showEventError() {
    }

    override fun onDataError() {
    }

    override fun onDataLoaded(data: EventNextLeagueResponse) {
    }
    
}
