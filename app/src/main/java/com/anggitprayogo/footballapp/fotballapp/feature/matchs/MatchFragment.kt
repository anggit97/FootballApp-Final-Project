package com.anggitprayogo.footballapp.fotballapp.feature.matchs

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.anggitprayogo.footballapp.fotballapp.R
import com.anggitprayogo.footballapp.fotballapp.adapter.TabAdapter
import com.anggitprayogo.footballapp.fotballapp.feature.searchmatch.SearchMatchActivity
import org.jetbrains.anko.support.v4.startActivity


class MatchFragment : Fragment(), MatchView{

    private lateinit var viewPager: ViewPager
    private lateinit var tabLayout: TabLayout
    private lateinit var ivSearch: ImageView
    private lateinit var adapter: TabAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_match, container, false)

        viewPager = view.findViewById(R.id.view_pager)
        tabLayout = view.findViewById(R.id.tab_layout)
        ivSearch = view.findViewById(R.id.iv_search)

        tabLayout.addTab(tabLayout.newTab().setText(activity?.getString(R.string.past_match)))
        tabLayout.addTab(tabLayout.newTab().setText(activity?.getString(R.string.next_match)))

        var adapter = TabAdapter(activity?.supportFragmentManager)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null){
                    viewPager.currentItem = tab.position
                }
            }

        })

        ivSearch.setOnClickListener {
            startActivity<SearchMatchActivity>()
        }

        // Inflate the layout for this fragment
        return view
    }
}
