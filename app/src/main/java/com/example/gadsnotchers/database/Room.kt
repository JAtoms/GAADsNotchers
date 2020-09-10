package com.example.gadsnotchers.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HoursDatabaseEntity::class, IQDatabaseEntity::class],
    version = 1, exportSchema = false)
abstract class GaadsDatabase : RoomDatabase() {

    abstract val gaadsDao: GaadsDao

    companion object {

        @Volatile
        private var INSTANCE: GaadsDatabase? = null
        fun getInstance(context: Context): GaadsDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {

                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        GaadsDatabase::class.java,
                        "gaads_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}