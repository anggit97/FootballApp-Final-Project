package com.anggitprayogo.footballapp.fotballapp.model.detailevents

import com.google.gson.annotations.SerializedName

data class DetailEventLeagueResponse(
        @SerializedName("events") val events: List<DetailEvent>
)