package com.anggitprayogo.footballapp.fotballapp.network.retrofit

import com.anggitprayogo.footballapp.fotballapp.model.detailevents.DetailEventLeagueResponse
import com.anggitprayogo.footballapp.fotballapp.model.detailteams.DetailTeamResponse
import com.anggitprayogo.footballapp.fotballapp.model.eventsnextleague.EventNext
import com.anggitprayogo.footballapp.fotballapp.model.eventsnextleague.EventNextLeagueResponse
import com.anggitprayogo.footballapp.fotballapp.model.eventspastleague.EventPast
import com.anggitprayogo.footballapp.fotballapp.model.eventspastleague.EventPastLeagueResponse
import com.anggitprayogo.footballapp.fotballapp.model.player.PlayerResponse
import com.anggitprayogo.footballapp.fotballapp.model.searchevent.SearchEventResponse
import com.anggitprayogo.footballapp.fotballapp.model.teams.TeamResponse
import com.kotlinje.submit2.model.detail_player.ResponseDetailPlayer
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitEndpoint {

    /**
     * Mendapatkan data event league (match)
     */
    @GET("api/v1/json/1/eventspastleague.php")
    fun getPastMatch(@Query("id")league:String): Call<EventPastLeagueResponse>
    @GET("api/v1/json/1/eventsnextleague.php")
    fun getNextMatch(@Query("id")leagueId:String): Call<EventNextLeagueResponse>
    @GET("api/v1/json/1/lookupevent.php")
    fun getDetailMatch(@Query("id")id: String): Call<DetailEventLeagueResponse>


    /**
     * Mendapatkan data team
     */
    @GET("api/v1/json/1/search_all_teams.php")
    fun getTeams(@Query("l") league: String?): Call<TeamResponse>
    @GET("api/v1/json/1/lookupteam.php")
    fun getDetailTeam(@Query("id") id: String?): Call<DetailTeamResponse>

    /**
     * Mendapatkan data player
     */
    @GET("api/v1/json/1/lookup_all_players.php")
    fun getTeamPlayer(@Query("id") idTeam:String?): Call<PlayerResponse>
    @GET("api/v1/json/1/lookupplayer.php")
    fun getDetailPlayer(@Query("id") idTeam:String?): Call<ResponseDetailPlayer>


    /**
     * Search
     */
    @GET("api/v1/json/1/searchteams.php")
    fun getSearchTeam(@Query("t") league: String?): Call<TeamResponse>
    @GET("api/v1/json/1/searchevents.php")
    fun getSearchMatch(@Query("e")e:String): Call<SearchEventResponse>

}