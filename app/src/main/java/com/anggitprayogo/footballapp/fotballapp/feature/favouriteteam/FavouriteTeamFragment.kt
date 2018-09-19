package com.anggitprayogo.footballapp.fotballapp.feature.favouriteteam

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.anggitprayogo.footballapp.fotballapp.R

class FavouriteTeamFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_favourite_team, container, false)

        // Inflate the layout for this fragment
        return view
    }
}
