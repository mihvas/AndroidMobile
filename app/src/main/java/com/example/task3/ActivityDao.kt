package com.example.task3

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ActivityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(activity: ActivityEntity)

    @Query("SELECT * FROM activities ORDER BY startTime DESC")
    fun getAllActivities(): LiveData<List<ActivityEntity>>

    @Query("DELETE FROM activities")
    suspend fun clearAll()
}