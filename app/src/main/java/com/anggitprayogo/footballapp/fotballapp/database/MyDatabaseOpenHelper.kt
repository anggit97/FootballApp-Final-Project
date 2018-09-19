package com.anggitprayogo.footballapp.fotballapp.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.anggitprayogo.footballapp.fotballapp.config.Config
import com.anggitprayogo.footballapp.fotballapp.model.favourite.FavouriteEvent
import com.anggitprayogo.footballapp.fotballapp.model.favourite.FavouriteTeam
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(context: Context):
        ManagedSQLiteOpenHelper(context, Config.DATABASE_NAME, null, 1) {

    companion object {
        private var instances: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstances(context: Context): MyDatabaseOpenHelper{
            if (instances == null){
                instances = MyDatabaseOpenHelper(context)
            }

            return instances!!;
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {

        db?.createTable(FavouriteEvent.TABLE_FAVOURITE_EVENT, true,
                FavouriteEvent.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavouriteEvent.EVENT_ID to TEXT + UNIQUE,
                FavouriteEvent.EVENT_NAME to TEXT,
                FavouriteEvent.HOME_NAME to TEXT,
                FavouriteEvent.HOME_SCORE to TEXT,
                FavouriteEvent.AWAY_NAME to TEXT,
                FavouriteEvent.AWAY_SCORE to TEXT,
                FavouriteEvent.DATE to TEXT,
                FavouriteEvent.TIME to TEXT)

        db?.createTable(FavouriteTeam.TABLE_NAME, true,
                FavouriteTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavouriteTeam.TEAM_ID to TEXT,
                FavouriteTeam.TEAM_NAME to TEXT,
                FavouriteTeam.TEAM_BADGE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        db?.dropTable(FavouriteEvent.TABLE_FAVOURITE_EVENT, true)
        db?.dropTable(FavouriteTeam.TABLE_NAME, true)

    }

}

val Context.database: MyDatabaseOpenHelper
get() = MyDatabaseOpenHelper.getInstances(applicationContext)