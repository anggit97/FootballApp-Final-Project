package com.anggitprayogo.footballapp.fotballapp.model.favourite

class FavouriteEvent(val id: Long?,
                     val eventId: String?,
                     val eventName: String?,
                     val strHomeName: String?,
                     val strAwayName: String?,
                     val strHomeScore: String?,
                     val strAwayScore: String?,
                     val strDate: String?,
                     val strTime: String?) {

    companion object {
        const val TABLE_FAVOURITE_EVENT = "table_event"
        const val ID = "id"
        const val EVENT_ID = "event_id"
        const val EVENT_NAME = "event_name"
        const val HOME_NAME = "home_name"
        const val AWAY_NAME = "away_name"
        const val HOME_SCORE = "home_score"
        const val AWAY_SCORE = "away_score"
        const val DATE = "date"
        const val TIME = "time"
    }

}