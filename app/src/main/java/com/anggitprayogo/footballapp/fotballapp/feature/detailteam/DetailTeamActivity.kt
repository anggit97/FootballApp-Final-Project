package com.anggitprayogo.footballapp.fotballapp.feature.detailteam

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import com.anggitprayogo.footballapp.fotballapp.R
import com.anggitprayogo.footballapp.fotballapp.adapter.DetailTeamTabAdapter
import com.anggitprayogo.footballapp.fotballapp.config.Config
import com.anggitprayogo.footballapp.fotballapp.model.detailteams.DetailTeam
import com.anggitprayogo.footballapp.fotballapp.model.detailteams.DetailTeamResponse
import com.anggitprayogo.footballapp.fotballapp.model.teams.Team
import com.anggitprayogo.footballapp.fotballapp.network.repository.MatchRepository
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_team.*

class DetailTeamActivity : AppCompatActivity(), DetailTeamView{

    private lateinit var adapter: DetailTeamTabAdapter
    private lateinit var presenter: DetailTeamPresenter

    var idTeam: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)

        idTeam = intent.getStringExtra(Config.LEAGUE_ID)

        tabs.addTab(tabs.newTab().setText(getString(R.string.overview)))
        tabs.addTab(tabs.newTab().setText(getString(R.string.player)))

        presenter = DetailTeamPresenter(this, MatchRepository())

        adapter = DetailTeamTabAdapter(supportFragmentManager, idTeam)
        viewpager.adapter = adapter
        viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null){
                    viewpager.currentItem = tab.position
                }
            }

        })

        presenter.getDetailTeam(idTeam)

        swipe_refresh_layout.setOnClickListener {
            swipe_refresh_layout.isRefreshing = false
            presenter.getDetailTeam(idTeam)
        }
    }

    override fun showLoading() {
        swipe_refresh_layout.isRefreshing = true
    }

    override fun hideLoading() {
        swipe_refresh_layout.isRefreshing = false
    }

    override fun showDetailTeamResponse(data: List<DetailTeam>) {

        val model = data.get(0)

        tv_club_name.text = model.strTeam
        tv_club_stadium.text = model.strStadium
        tv_club_year.text = model.intFormedYear

        Glide.with(this)
                .load(model.strTeamBadge)
                .into(iv_club)

    }

    override fun showDetailTeamError() {
    }

    override fun onDataLoaded(data: DetailTeamResponse) {
    }

    override fun onDataError() {
    }

}
