package com.example.albalog.ui.calendar

import android.graphics.Color
import android.view.View
import android.widget.TextView
import com.example.albalog.R
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.view.MonthDayBinder
import com.kizitonwose.calendar.view.ViewContainer
import java.time.LocalDate

class CalendarDayBinder(
    private val getSelectedDate: () -> LocalDate?,
    private val getEvents: () -> Set<LocalDate>,
    private val onDateSelected: (LocalDate) -> Unit
) : MonthDayBinder<CalendarDayBinder.DayViewContainer> {

    inner class DayViewContainer(view: View) : ViewContainer(view) {
        val textView: TextView = view.findViewById(R.id.calendarDayText)
        val bgView: View = view.findViewById(R.id.viewSelectedBackground)
        val dotView: View = view.findViewById(R.id.viewDot)
        lateinit var day: CalendarDay

        init {
            view.setOnClickListener {
                if (day.position == DayPosition.MonthDate) {
                    onDateSelected(day.date)
                }
            }
        }
    }

    override fun create(view: View) = DayViewContainer(view)

    override fun bind(container: DayViewContainer, day: CalendarDay) {
        val selectedDate = getSelectedDate()
        val events = getEvents()

        container.day = day
        container.textView.text = day.date.dayOfMonth.toString()

        val isSelected = selectedDate == day.date
        val isEvent = events.contains(day.date)
        val isInMonth = day.position == DayPosition.MonthDate

        if (day.date == LocalDate.now()) {
            // 오늘 날짜 스타일 적용: 텍스트 색상 변경 + 테두리 배경 적용
            container.textView.setTextColor(Color.parseColor("#FF5722"))
            container.textView.setBackgroundResource(R.drawable.bg_today_border)
        } else {
            container.textView.setTextColor(
                when {
                    !isInMonth -> Color.LTGRAY
                    isSelected -> Color.WHITE
                    else -> Color.BLACK
                }
            )
            container.textView.background = null // 오늘 날짜 아니면 백그라운드 초기화
        }
        // 선택된 날짜 배경 표시
        container.bgView.visibility = if (isSelected) View.VISIBLE else View.GONE

        // 이벤트 날짜 점 표시
        container.dotView.visibility = if (isEvent) View.VISIBLE else View.GONE

        // 텍스트 색상 처리
        container.textView.setTextColor(
            when {
                !isInMonth -> Color.LTGRAY
                isSelected -> Color.WHITE
                else -> Color.BLACK
            }
        )
    }
}
