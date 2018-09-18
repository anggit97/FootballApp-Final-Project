package com.anggitprayogo.footballapp.fotballapp.model.eventspastleague

import com.google.gson.annotations.SerializedName

data class EventPastLeagueResponse(
        @SerializedName("events") val events: List<EventPast>
)