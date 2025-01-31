package com.example.task3

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "activities")
data class ActivityEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val type: String,
    val startTime: Long,
    val endTime: Long,
    val coordinates: String
)
