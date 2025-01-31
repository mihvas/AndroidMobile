package com.example.task3

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ActivityEntity::class], version = 1)
abstract class ActivityDatabase : RoomDatabase() {
    abstract fun activityDao(): ActivityDao

    companion object {
        @Volatile
        private var INSTANCE: ActivityDatabase? = null

        fun getDatabase(context: Context): ActivityDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ActivityDatabase::class.java,
                    "activity_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
