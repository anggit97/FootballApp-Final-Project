package com.anggitprayogo.footballapp.fotballapp.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.anggitprayogo.footballapp.fotballapp.feature.detailteamoverview.OverviewFragment
import com.anggitprayogo.footballapp.fotballapp.feature.detailteamplayer.PlayerFragment

class DetailTeamTabAdapter (fm: FragmentManager?, val id: String?) : FragmentStatePagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {
        if (position == 0) {
            return OverviewFragment.newInstance(id!!, "")
        } else {
            return PlayerFragment.newInstance(id!!, "")
        }

    }

    override fun getCount(): Int {
        return 2
    }
}