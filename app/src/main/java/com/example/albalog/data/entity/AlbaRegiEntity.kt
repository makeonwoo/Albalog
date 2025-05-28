package com.example.albalog.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alba_regi_table")
data class AlbaRegiEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val startTime: String,
    val endTime: String,
    val repeatDays: String,
)
