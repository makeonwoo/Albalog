package com.example.albalog.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.albalog.data.dao.AlbaRegiDao
import com.example.albalog.data.entity.AlbaRegiEntity

@Database(entities = [AlbaRegiEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun albaDao(): AlbaRegiDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "alba_database"
                ).build().also { INSTANCE = it }
            }
    }
}
