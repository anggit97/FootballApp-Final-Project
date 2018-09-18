package com.anggitprayogo.footballapp.fotballapp.feature.eventpastleague

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
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
import com.anggitprayogo.footballapp.fotballapp.adapter.EventPastLeagueAdapter
import com.anggitprayogo.footballapp.fotballapp.model.eventspastleague.EventPast
import com.anggitprayogo.footballapp.fotballapp.model.eventspastleague.EventPastLeagueResponse
import com.anggitprayogo.footballapp.fotballapp.network.repository.MatchRepository
import kotlinx.android.synthetic.main.fragment_event_past_league.*
import org.jetbrains.anko.support.v4.ctx

class EventPastLeagueFragment : Fragment(), EventPastLeagueView {

    private lateinit var adapter: EventPastLeagueAdapter
    private lateinit var presenter: EventPastLeaguePresenter
    private lateinit var rvPastEvent: RecyclerView
    private lateinit var spinner: Spinner
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    var teamsName: String = "4238"

    var datas: MutableList<EventPast> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_event_past_league, container, false)

        spinner = view.findViewById(R.id.spn_league)
        rvPastEvent = view.findViewById(R.id.rv_league)
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout)

        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        presenter = EventPastLeaguePresenter(this, MatchRepository())


        val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        rvPastEvent.layoutManager = linearLayoutManager
        adapter = EventPastLeagueAdapter(datas,{
            Toast.makeText(activity, it.dateEvent, Toast.LENGTH_SHORT).show()
        })
        rvPastEvent.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {


            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                teamsName = spinner.selectedItem.toString()

                if (position== 0){
                    teamsName ="4328"
                }
                else if (position==1){
                    teamsName="4329"
                } else if (position==2){
                    teamsName="4331"
                } else if (position==3){
                    teamsName="4332"
                } else if (position==4){
                    teamsName="4334"
                } else if (position==5){
                    teamsName="4335"
                }

                Log.e("Tag", "spinner :" + teamsName)
                presenter?.getPastEvent(teamsName)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            presenter.getPastEvent(teamsName)
        }


        // Inflate the layout for this fragment
        return view
    }

    override fun showLoading() {
        swipe_refresh_layout.isRefreshing = true
    }

    override fun hideProgress() {
        swipe_refresh_layout.isRefreshing = false
    }

    override fun showToast(message: String?) {
    }

    override fun showEventList(data: List<EventPast>) {
        datas.clear()
        datas.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun onDataLoaded(data: EventPastLeagueResponse) {
    }

    override fun onDataError() {
    }

    override fun showDataLoaded() {
    }

    override fun showEventError() {
    }

}
