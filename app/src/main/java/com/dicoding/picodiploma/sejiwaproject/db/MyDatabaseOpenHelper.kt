package com.dicoding.picodiploma.sejiwaproject.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) :
    ManagedSQLiteOpenHelper(ctx, "FavoriteMatch.db", null, 4) {
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
            Favorite.TABLE_FAVORITE, true,
            Favorite.MATCH_ID to TEXT + PRIMARY_KEY,
            Favorite.TEAM_HOME to TEXT + UNIQUE,
            Favorite.TEAM_AWAY to TEXT,
            Favorite.DATE_MATCH to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(Favorite.TABLE_FAVORITE, true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)