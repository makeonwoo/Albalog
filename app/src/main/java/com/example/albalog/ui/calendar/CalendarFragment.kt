package com.example.albalog.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.albalog.R
import com.example.albalog.databinding.FragmentCalendarBinding
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.view.ViewContainer
import java.time.LocalDate
import com.kizitonwose.calendar.view.MonthDayBinder
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class CalendarFragment : Fragment() {

    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!

    private val today = LocalDate.now()
    private val monthFormatter = DateTimeFormatter.ofPattern("yyyy년 MM월")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val startMonth = YearMonth.now().minusMonths(12)
        val endMonth = YearMonth.now().plusMonths(12)
        val firstDayOfWeek = java.time.DayOfWeek.SUNDAY

        // CalendarView 범위 설정
        binding.calendarView.setup(startMonth, endMonth, firstDayOfWeek)
        // scrollToMonth 현재 월로 바로 보여주기
        binding.calendarView.scrollToMonth(YearMonth.now())

        // 스크롤로 달력 이동시 월(상단 텍스트) 갱신해주기
        binding.calendarView.monthScrollListener = { month ->
            binding.txtMonth.text = monthFormatter.format(month.yearMonth)
        }

        binding.calendarView.dayBinder = object : MonthDayBinder<DayViewContainer> {
            override fun create(view: View) = DayViewContainer(view)

            override fun bind(container: DayViewContainer, day: CalendarDay) {
                val textView = container.textView
                val date = day.date
                textView.text = date.dayOfMonth.toString()

                if (day.position == DayPosition.MonthDate) {
                    textView.visibility = View.VISIBLE
                } else {
                    textView.visibility = View.INVISIBLE
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    inner class DayViewContainer(view: View) : ViewContainer(view) {
        val textView = view.findViewById<TextView>(R.id.calendarDayText)
    }
}