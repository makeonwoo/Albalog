package com.example.albalog.ui.alba

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.albalog.data.AppDatabase
import com.example.albalog.data.entity.AlbaRegiEntity
import com.example.albalog.data.repository.AlbaRepository
import kotlinx.coroutines.launch

class AlbaViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase.getInstance(application).albaDao()
    private val repository = AlbaRepository(dao)

    val albaList = repository.getAllAlbas().asLiveData()

    fun insertAlba(title: String, start: String, end: String, days: List<String>) {
        viewModelScope.launch {
            val alba = AlbaRegiEntity(
                title = title,
                startTime = start,
                endTime = end,
                repeatDays = days.joinToString(",")
            )
            repository.insert(alba)
        }
    }
}
