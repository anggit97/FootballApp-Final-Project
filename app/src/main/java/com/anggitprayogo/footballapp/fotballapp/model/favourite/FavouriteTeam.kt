package com.anggitprayogo.footballapp.fotballapp.model.favourite

class FavouriteTeam (val id: Long?,
                     val teamId: String?,
                     val teamName: String?,
                     val teamBadge: String?){

    companion object {
        const val TABLE_NAME = "table_team"
        const val ID = "id"
        const val TEAM_ID = "team_id"
        const val TEAM_NAME = "team_name"
        const val TEAM_BADGE = "team_badge"
    }

}