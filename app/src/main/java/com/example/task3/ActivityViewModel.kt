package com.example.task3

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ActivityViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ActivityRepository
    val allActivities: LiveData<List<ActivityEntity>>

    init {
        val dao = ActivityDatabase.getDatabase(application).activityDao()
        repository = ActivityRepository(dao)
        allActivities = repository.allActivities
    }

    fun insert(activity: ActivityEntity) = viewModelScope.launch {
        repository.insert(activity)
    }

    fun clearAll() = viewModelScope.launch {
        repository.clearAll()
    }
}