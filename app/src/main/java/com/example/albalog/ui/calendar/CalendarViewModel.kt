package com.example.albalog.ui.calendar

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate

class CalendarViewModel : ViewModel() {

    val selectedDate = MutableLiveData<LocalDate>()
    val albaList = MutableLiveData<List<AlbaItem>>()
    val weeklyGoals = MutableLiveData<List<GoalItem>>()

    init {
        // 초기값 설정 (예: 오늘 날짜)
        selectedDate.value = LocalDate.now()
        loadMockData(selectedDate.value!!)
    }

    fun onDateSelected(date: LocalDate) {
        selectedDate.value = date
        loadMockData(date)
    }

    // 임시 목업 데이터
    private fun loadMockData(date: LocalDate) {
        albaList.value = listOf(
            AlbaItem("Convenience store", "10:00 AM – 2 hr", 25.0)
        )
        weeklyGoals.value = listOf(
            GoalItem("Exercise 1 hour", "Mon. Thu", 3, 4)
        )
    }
}

data class AlbaItem(val title: String, val time: String, val wage: Double)
data class GoalItem(val title: String, val days: String, val done: Int, val total: Int)