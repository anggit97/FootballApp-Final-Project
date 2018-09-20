package com.anggitprayogo.footballapp.fotballapp.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.anggitprayogo.footballapp.fotballapp.feature.favouriteevent.FavouriteEventFragment
import com.anggitprayogo.footballapp.fotballapp.feature.favouriteteam.FavouriteTeamFragment

class FavouriteTabAdapter (fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        if (position == 0) {
            return FavouriteEventFragment()
        } else {
            return FavouriteTeamFragment()
        }

    }

    override fun getCount(): Int {
        return 2
    }
}