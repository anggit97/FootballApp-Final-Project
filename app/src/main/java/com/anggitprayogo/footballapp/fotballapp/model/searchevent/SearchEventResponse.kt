package com.anggitprayogo.footballapp.fotballapp.model.searchevent

import com.google.gson.annotations.SerializedName

data class SearchEventResponse(
        @SerializedName("event") val event: List<SearchEvent>
)