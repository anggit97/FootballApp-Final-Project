package com.anggitprayogo.footballapp.fotballapp.feature.mainactivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import com.anggitprayogo.footballapp.fotballapp.R
import com.anggitprayogo.footballapp.fotballapp.feature.favourites.FavouriteFragment
import com.anggitprayogo.footballapp.fotballapp.feature.matchs.MatchFragment
import com.anggitprayogo.footballapp.fotballapp.feature.teams.TeamFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityView{

    private lateinit var menu: Menu
    private lateinit var presenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Menampilkan fragment pertama kali aplikasi terbuka
        showFragment(MatchFragment())

        //Selected listener pada nav bottom
        navigation_bottom.setOnNavigationItemSelectedListener{

            var fragment: Fragment? = null

            when(it.itemId){
                R.id.nav_matches -> {
                    fragment = MatchFragment()
                    showFragment(fragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_teams -> {
                    fragment = TeamFragment()
                    showFragment(fragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_favourite -> {
                    fragment = FavouriteFragment()
                    showFragment(fragment)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            return@setOnNavigationItemSelectedListener false

        }
    }


    private fun showFragment(fragment: Fragment?) {
        val mgr = supportFragmentManager
        mgr.beginTransaction().replace(R.id.fl_container, fragment).commit()

    }
}
