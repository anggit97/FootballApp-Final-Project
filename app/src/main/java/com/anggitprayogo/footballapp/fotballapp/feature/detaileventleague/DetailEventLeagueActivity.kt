package com.anggitprayogo.footballapp.fotballapp.feature.detaileventleague

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.util.Log
import com.anggitprayogo.footballapp.fotballapp.R
import com.anggitprayogo.footballapp.fotballapp.config.Config
import com.anggitprayogo.footballapp.fotballapp.model.detailevents.DetailEvent
import com.anggitprayogo.footballapp.fotballapp.model.detailevents.DetailEventLeagueResponse
import com.anggitprayogo.footballapp.fotballapp.model.detailteams.DetailTeam
import com.anggitprayogo.footballapp.fotballapp.network.repository.MatchRepository
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_event_league.*

class DetailEventLeagueActivity : AppCompatActivity(), DetailEventLeagueView{

    private lateinit var presenter: DetailEventLeaguePresenter

    var detailevent: MutableList<DetailEvent> = mutableListOf()
    var idEvent: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_event_league)

        idEvent = intent.getStringExtra(Config.LEAGUE_ID)

        Log.d("ID EVENT ",idEvent)

        presenter = DetailEventLeaguePresenter(this, MatchRepository())

        presenter.getDetailMatch(idEvent)

        swipe_refresh_layout.setOnRefreshListener {
            swipe_refresh_layout.isRefreshing = false
            presenter.getDetailMatch(idEvent)
        }

    }

    override fun showLoading() {
        swipe_refresh_layout.isRefreshing = true
    }

    override fun hideLoading() {
        swipe_refresh_layout.isRefreshing = false
    }

    override fun showDetailEventResponse(datas: List<DetailEvent>) {
        detailevent.clear()
        detailevent.addAll(datas)

        Log.d("DATAS ", ""+datas.size)

        var model = datas.get(0)

        tv_date.text = model.strDate
        tv_hours.text = model.strTime
        tv_home_score.text = model.intHomeScore
        tv_away_score.text = model.intAwayScore
        tv_home_name.text = model.strHomeTeam
        tv_away_name.text = model.strAwayTeam
        tv_home_formation.text = if (model.strHomeFormation.isEmpty()) getString(R.string.formation_not_found) else model.strHomeFormation
        tv_away_formation.text = if (model.strAwayFormation.isEmpty()) getString(R.string.formation_not_found) else model.strAwayFormation
        tv_home_player_scored.text = model.strHomeGoalDetails
        tv_away_player_scored.text = model.strAwayGoalDetails
        tv_home_shots.text = model.intHomeShots
        tv_away_shots.text = model.intAwayShots
        tv_home_goalkeeper.text = model.strHomeLineupGoalkeeper
        tv_away_goolkeeprer.text = model.strAwayLineupGoalkeeper
        tv_home_midfield.text = model.strHomeLineupMidfield
        tv_away_midfield.text = model.strAwayLineupMidfield
        tv_home_deffense.text = model.strHomeLineupDefense
        tv_away_deffense.text = model.strAwayLineupDefense

        presenter.getDetailHomeTeam(model.idHomeTeam)
        presenter.getDetailAwayTeam(model.idAwayTeam)

    }

    override fun showDetailEventError() {
        Log.d("showDetailEventError ","ERROR")
    }

    override fun onDataLoaded(data: DetailEventLeagueResponse) {
    }

    override fun onDataError() {
        Log.d("onDataError ","ERROR")
    }

    override fun showDetailHomeTeamResponse(data: List<DetailTeam>) {
        Glide.with(this)
                .load(data.get(0).strTeamBadge)
                .into(iv_home_badge)
    }

    override fun showDetailHomeTeamError() {
    }

    override fun showDetailAwayTeamResponse(data: List<DetailTeam>) {
        Glide.with(this)
                .load(data.get(0).strTeamBadge)
                .into(iv_away_badge)
    }

    override fun showDetailAwayTeamError() {
    }
}