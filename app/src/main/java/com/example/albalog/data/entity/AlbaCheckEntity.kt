package com.example.albalog.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alba_regi_table")
data class AlbaCheckEntity( //TODO 알바 햇는지 여부 등록하는 DB
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val startTime: String,
    val endTime: String,
    //체크
)
