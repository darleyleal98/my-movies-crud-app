package com.darleyleal.movieapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.darleyleal.movieapp.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MovieAppDatabase : RoomDatabase() {
    abstract fun movieAppDAO(): MovieAppDAO

    companion object {
        private lateinit var INSTANCE: MovieAppDatabase

        fun getDatabase(context: Context): MovieAppDatabase {
            if (!::INSTANCE.isInitialized) {
                synchronized(MovieAppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        MovieAppDatabase::class.java, "movieapp"
                    ).addMigrations(MIGRATION)
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }

        private val MIGRATION: Migration = object: Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("DELETE FROM movieapp")
            }
        }
    }
}