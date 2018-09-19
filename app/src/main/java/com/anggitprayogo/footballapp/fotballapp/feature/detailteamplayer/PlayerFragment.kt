package com.anggitprayogo.footballapp.fotballapp.feature.detailteamplayer

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.anggitprayogo.footballapp.fotballapp.R
import com.anggitprayogo.footballapp.fotballapp.adapter.PlayerAdapter
import com.anggitprayogo.footballapp.fotballapp.config.Config
import com.anggitprayogo.footballapp.fotballapp.feature.detailplayer.DetailPlayerActivity
import com.anggitprayogo.footballapp.fotballapp.feature.detailteamoverview.OverviewFragment
import com.anggitprayogo.footballapp.fotballapp.model.player.Player
import com.anggitprayogo.footballapp.fotballapp.model.player.PlayerResponse
import com.anggitprayogo.footballapp.fotballapp.network.repository.MatchRepository
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast


class PlayerFragment : Fragment(), PlayerView {

    private var mParam1: String? = null
    private var mParam2: String? = null
    private lateinit var presenter: PlayerPresenter
    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var adapter: PlayerAdapter

    var datas: MutableList<Player> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments!!.getString(OverviewFragment.ARG_PARAM1)
            mParam2 = arguments!!.getString(OverviewFragment.ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_player, container, false)

        recyclerView = view.findViewById(R.id.rv_player)
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout)

        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        adapter = PlayerAdapter(datas) {
            startActivity<DetailPlayerActivity>(
                    Config.LEAGUE_ID to it.idPlayer
            )
        }
        recyclerView.adapter = adapter

        presenter = PlayerPresenter(this, MatchRepository())

        presenter.getPlayer(mParam1!!)

        toast(mParam1!!)

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            presenter.getPlayer(mParam1!!)
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

    override fun showPlayerResponse(data: List<Player>) {
        datas.clear()
        datas.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun showPlayerError() {
    }

    override fun onDataLoaded(data: PlayerResponse) {
    }

    override fun onDataError() {
    }

    companion object {
        val ARG_PARAM1 = "param1"
        val ARG_PARAM2 = "param2"

        fun newInstance(param1: String, param2: String): PlayerFragment {
            val fragment = PlayerFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }

}
