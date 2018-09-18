package com.anggitprayogo.footballapp.fotballapp.model.eventsnextleague

import com.google.gson.annotations.SerializedName

data class EventNextLeagueResponse(
        @SerializedName("events") val events: List<EventNext>
)