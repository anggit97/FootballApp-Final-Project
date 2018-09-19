package com.anggitprayogo.footballapp.fotballapp.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.anggitprayogo.footballapp.fotballapp.R
import com.anggitprayogo.footballapp.fotballapp.model.player.Player
import com.bumptech.glide.Glide

class PlayerAdapter(val datas: List<Player>,
                    val listener: (Player) -> Unit) : RecyclerView.Adapter<PlayerAdapter.ViewHolder>() {


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val tvName = view.findViewById<TextView>(R.id.tv_player)
        val tvPosition = view.findViewById<TextView>(R.id.tv_position)
        val ivPlayer = view.findViewById<ImageView>(R.id.iv_player)

        fun bindItems(listener: (Player) -> Unit, datas: Player) {

            tvName.text = datas.strPlayer
            tvPosition.text = datas.strPosition

            Glide.with(itemView.context)
                    .load(datas.strCutout)
                    .into(ivPlayer)

            itemView.setOnClickListener {
                listener(datas)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_player_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int  = datas.size

    override fun onBindViewHolder(holder: PlayerAdapter.ViewHolder, position: Int) {
        holder.bindItems(listener, datas.get(position))
    }
}