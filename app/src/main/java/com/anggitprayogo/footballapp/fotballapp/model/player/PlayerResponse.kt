package com.anggitprayogo.footballapp.fotballapp.model.player

import com.google.gson.annotations.SerializedName

data class PlayerResponse(
        @SerializedName("player") val player: List<Player>
)