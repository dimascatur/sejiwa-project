package com.dicoding.picodiploma.sejiwaproject.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) :
    ManagedSQLiteOpenHelper(ctx, "FavoriteMatch.db", null, 5) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(
            FavoriteMatch.TABLE_FAVORITE, true,
            FavoriteMatch.MATCH_ID to TEXT + PRIMARY_KEY,
            FavoriteMatch.TEAM_HOME to TEXT + UNIQUE,
            FavoriteMatch.TEAM_AWAY to TEXT,
            FavoriteMatch.DATE_MATCH to TEXT,
            FavoriteMatch.BADGE_HOME to TEXT,
            FavoriteMatch.BADGE_AWAY to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(FavoriteMatch.TABLE_FAVORITE, true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)