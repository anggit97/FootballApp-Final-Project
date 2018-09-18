package com.anggitprayogo.footballapp.fotballapp.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.anggitprayogo.footballapp.fotballapp.feature.eventnextleague.EventNextLeagueFragment
import com.anggitprayogo.footballapp.fotballapp.feature.eventpastleague.EventPastLeagueFragment

class TabAdapter (fm: FragmentManager?) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        if (position == 0) {
            return EventPastLeagueFragment()
        } else {
            return EventNextLeagueFragment()
        }

    }

    override fun getCount(): Int {
        return 2
    }
}