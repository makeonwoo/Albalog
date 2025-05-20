package com.example.albalog.ui.calendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.YearMonth

class CalendarViewModel : ViewModel() {

    private val _selectedDate = MutableLiveData<LocalDate?>()
    val selectedDate: LiveData<LocalDate?> = _selectedDate

    private val _eventDates = MutableLiveData<Set<LocalDate>>()
    val eventDates: LiveData<Set<LocalDate>> = _eventDates

    fun selectDate(date: LocalDate) {
        _selectedDate.value = if (_selectedDate.value == date) null else date
    }

    fun loadEvents(month: YearMonth) {
        //TODO 특정 날짜에 도트 표시할때 사용 - 데이터 세팅 이후에 추가 수정
        _eventDates.value = listOf(
            LocalDate.of(month.year, month.month, 5),
            LocalDate.of(month.year, month.month, 12)
        ).toSet()
    }
}