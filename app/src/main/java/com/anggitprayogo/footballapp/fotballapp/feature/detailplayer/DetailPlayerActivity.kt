package com.anggitprayogo.footballapp.fotballapp.feature.detailplayer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.anggitprayogo.footballapp.fotballapp.R
import com.anggitprayogo.footballapp.fotballapp.config.Config
import com.anggitprayogo.footballapp.fotballapp.model.player.PlayerResponse
import com.anggitprayogo.footballapp.fotballapp.network.repository.MatchRepository
import com.bumptech.glide.Glide
import com.kotlinje.submit2.model.detail_player.PlayersItemDetail
import kotlinx.android.synthetic.main.activity_detail_player.*

class DetailPlayerActivity : AppCompatActivity(), DetailPlayerView{

    private lateinit var presenter: DetailPlayerPresenter

    var idPlayer: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)

        idPlayer = intent.getStringExtra(Config.LEAGUE_ID)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = DetailPlayerPresenter(this, MatchRepository())

        presenter.getDetailPlayer(idPlayer)

        swipe_refresh_layout.setOnRefreshListener {
            presenter.getDetailPlayer(idPlayer)
        }
    }

    override fun showLoading() {
        swipe_refresh_layout.isRefreshing = true
    }

    override fun hideLoading() {
        swipe_refresh_layout.isRefreshing = false
    }

    override fun showPlayerResponse(data: List<PlayersItemDetail?>?) {
        val model = data?.get(0)

        tv_weight.text = model?.strWeight
        tv_height.text = model?.strHeight
        tv_desc.text = model?.strDescriptionEN
        tv_position.text = model?.strPosition

        toolbar.setTitle(model?.strPlayer)

        Glide.with(this)
                .load(model?.strFanart1)
                .into(iv_player)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            R.id.home -> {
                finish()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showPlayerError() {

    }

    override fun onDataLoaded(data: PlayerResponse) {

    }

    override fun onDataError() {

    }
}
