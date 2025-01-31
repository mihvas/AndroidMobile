package com.example.task3

import androidx.lifecycle.LiveData

class ActivityRepository(private val activityDao: ActivityDao) {
    val allActivities: LiveData<List<ActivityEntity>> = activityDao.getAllActivities()

    suspend fun insert(activity: ActivityEntity) {
        activityDao.insert(activity)
    }

    suspend fun clearAll() {
        activityDao.clearAll()
    }
}