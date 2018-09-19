package com.anggitprayogo.footballapp.fotballapp.model.detailteams

import com.google.gson.annotations.SerializedName

data class DetailTeamResponse(
        @SerializedName("teams") val teams: List<DetailTeam>
)