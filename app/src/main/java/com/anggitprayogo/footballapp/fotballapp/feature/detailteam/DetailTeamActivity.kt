package com.anggitprayogo.footballapp.fotballapp.feature.detailteam

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.anggitprayogo.footballapp.fotballapp.R
import com.anggitprayogo.footballapp.fotballapp.adapter.DetailTeamTabAdapter
import com.anggitprayogo.footballapp.fotballapp.config.Config
import com.anggitprayogo.footballapp.fotballapp.database.database
import com.anggitprayogo.footballapp.fotballapp.model.detailteams.DetailTeam
import com.anggitprayogo.footballapp.fotballapp.model.detailteams.DetailTeamResponse
import com.anggitprayogo.footballapp.fotballapp.model.favourite.FavouriteEvent
import com.anggitprayogo.footballapp.fotballapp.model.favourite.FavouriteTeam
import com.anggitprayogo.footballapp.fotballapp.model.teams.Team
import com.anggitprayogo.footballapp.fotballapp.network.repository.MatchRepository
import com.bumptech.glide.Glide
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.toast

class DetailTeamActivity : AppCompatActivity(), DetailTeamView{

    private lateinit var adapter: DetailTeamTabAdapter
    private lateinit var presenter: DetailTeamPresenter

    var menuItem: Menu? = null
    var favourite: Boolean? = null
    var idTeam: String = ""
    var teamBadge: String = ""
    var teamName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.title = null

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

        checkFavourite();

        swipe_refresh_layout.setOnClickListener {
            swipe_refresh_layout.isRefreshing = false
            presenter.getDetailTeam(idTeam)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail_activity, menu)
        menuItem = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            R.id.home -> {
                finish()
                return true
            }
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
            else -> {
                return super.onOptionsItemSelected(item)
            }
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

        teamName = model.strTeam
        teamBadge = model.strTeamBadge

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
                val queryBuilder = select(FavouriteTeam.TABLE_NAME)
                        .whereArgs("(${FavouriteTeam.TEAM_ID} = {id})",
                                "id" to idTeam)
                val result = queryBuilder.parseList(classParser<FavouriteTeam>())

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
                insert(FavouriteTeam.TABLE_NAME,
                        FavouriteTeam.TEAM_ID to idTeam,
                       FavouriteTeam.TEAM_BADGE to teamBadge,
                        FavouriteTeam.TEAM_NAME to teamName)
            }

            Toast.makeText(this, getString(R.string.message_favourite_add_success), Toast.LENGTH_LONG).show()

        }catch (e: SQLiteConstraintException){

            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()

        }

    }

    fun removeFromFavourite(){

        try {

            database.use {
                delete(FavouriteTeam.TABLE_NAME,
                        "( ${FavouriteTeam.TEAM_ID} = {event_id})",
                        "event_id" to idTeam)
            }

            Toast.makeText(this, getString(R.string.message_favourite_remove_success), Toast.LENGTH_LONG).show()


        }catch (e: SQLiteConstraintException){

            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()

        }

    }

}
