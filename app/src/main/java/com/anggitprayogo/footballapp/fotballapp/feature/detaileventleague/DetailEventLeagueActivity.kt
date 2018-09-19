package com.anggitprayogo.footballapp.fotballapp.feature.detaileventleague

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.anggitprayogo.footballapp.fotballapp.R
import com.anggitprayogo.footballapp.fotballapp.config.Config
import com.anggitprayogo.footballapp.fotballapp.database.database
import com.anggitprayogo.footballapp.fotballapp.model.detailevents.DetailEvent
import com.anggitprayogo.footballapp.fotballapp.model.detailevents.DetailEventLeagueResponse
import com.anggitprayogo.footballapp.fotballapp.model.detailteams.DetailTeam
import com.anggitprayogo.footballapp.fotballapp.model.favourite.FavouriteEvent
import com.anggitprayogo.footballapp.fotballapp.network.repository.MatchRepository
import com.bumptech.glide.Glide
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_detail_event_league.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class DetailEventLeagueActivity : AppCompatActivity(), DetailEventLeagueView{

    private lateinit var presenter: DetailEventLeaguePresenter

    var detailevent: MutableList<DetailEvent> = mutableListOf()
    var idEvent: String = ""
    var nameEvent: String? = null
    var homeScoreEvent: String? = null
    var homeNameEvent: String? = null
    var awayScoreEvent: String? = null
    var awayNameEvent: String? = null
    var dateEvent: String? = null
    var timeEvent: String? = null
    var menuItem: Menu? = null
    var favourite: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_event_league)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        idEvent = intent.getStringExtra(Config.LEAGUE_ID)

        Log.d("ID EVENT ",idEvent)

        presenter = DetailEventLeaguePresenter(this, MatchRepository())

        presenter.getDetailMatch(idEvent)

        checkFavourite();

        swipe_refresh_layout.setOnRefreshListener {
            swipe_refresh_layout.isRefreshing = false
            presenter.getDetailMatch(idEvent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail_activity, menu)
        menuItem = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            R.id.nav_favourite -> {

                if (favourite!!) {
                    removeFromFavourite()
                    favourite = false
                } else {
                    // add to favorite
                    insertToFavourite()
                    favourite = true
                }

                //set favorite
                setFavourite(favourite!!)

                return true
            }
            R.id.home ->{
                finish()
                return true
            }
            else -> super.onOptionsItemSelected(item)
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

        nameEvent = model.strEvent
        homeScoreEvent = model.intHomeScore
        homeNameEvent = model.strHomeTeam
        awayNameEvent = model.strAwayTeam
        awayScoreEvent = model.intAwayScore
        dateEvent = model.strDate
        timeEvent = model.strTime

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
        tv_home_forward.text = model.strHomeFormation
        tv_away_forward.text = model.strAwayLineupForward

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



    fun setFavourite(favourite: Boolean){

        Log.d("FAVOURITE ", ""+favourite)

        if (favourite) {
            Log.d("FAVOURITE ", "Ada")

            menuItem?.getItem(0)?.icon =
                    ContextCompat.getDrawable(this, R.drawable.ic_favorite_black_24dp)
        } else {
            Log.d("FAVOURITE ", "Gak ada")

            menuItem?.getItem(0)?.icon =
                    ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_black_24dp)
        }
    }

    fun checkFavourite() {
        try {

            database.use {
                val queryBuilder = select(FavouriteEvent.TABLE_FAVOURITE_EVENT)
                        .whereArgs("(${FavouriteEvent.EVENT_ID} = {id})",
                                "id" to idEvent)
                val result = queryBuilder.parseList(classParser<FavouriteEvent>())

                Log.d("RESULT : ", GsonBuilder().setPrettyPrinting().create().toJson(result))

                favourite = !result.isEmpty()
                setFavourite(favourite!!)
            }

        }catch (e: SQLiteConstraintException){
            toast(e.message!!)
        }
    }

    fun insertToFavourite(){

        try {

            database.use {
                insert(FavouriteEvent.TABLE_FAVOURITE_EVENT,
                        FavouriteEvent.EVENT_ID to idEvent,
                        FavouriteEvent.EVENT_NAME to nameEvent,
                        FavouriteEvent.HOME_NAME to homeNameEvent,
                        FavouriteEvent.HOME_SCORE to homeScoreEvent,
                        FavouriteEvent.AWAY_NAME to awayNameEvent,
                        FavouriteEvent.AWAY_SCORE to awayScoreEvent,
                        FavouriteEvent.DATE to dateEvent,
                        FavouriteEvent.TIME to timeEvent)
            }

            Toast.makeText(this, getString(R.string.message_favourite_add_success), Toast.LENGTH_LONG).show()

        }catch (e: SQLiteConstraintException){

            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()

        }

    }

    fun removeFromFavourite(){

        try {

            database.use {
                delete(FavouriteEvent.TABLE_FAVOURITE_EVENT,
                        "( ${FavouriteEvent.EVENT_ID} = {event_id})",
                        "event_id" to idEvent)
            }

            Toast.makeText(this, getString(R.string.message_favourite_remove_success), Toast.LENGTH_LONG).show()


        }catch (e: SQLiteConstraintException){

            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()

        }

    }
}