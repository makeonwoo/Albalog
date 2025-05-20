package com.example.albalog.ui.calendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.albalog.R
import com.example.albalog.databinding.FragmentCalendarBinding
import com.kizitonwose.calendar.view.CalendarView
import java.time.DayOfWeek
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.Locale

class CalendarFragment : Fragment() {

    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CalendarViewModel
    private lateinit var calendarView: CalendarView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalendarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this)[CalendarViewModel::class.java]
        calendarView = binding.calendarView

        setupCalendar()
        observeViewModel()
    }

    private fun setupCalendar() {
        val today = YearMonth.now()
        val startMonth = today.minusMonths(12)
        val endMonth = today.plusMonths(12)
        val firstDayOfWeek = DayOfWeek.SUNDAY

        calendarView.setup(startMonth, endMonth, firstDayOfWeek)
        calendarView.scrollToMonth(today)

        // 월 스크롤 시 상단 텍스트 갱신 + 이벤트 불러오기
        calendarView.monthScrollListener = {
            val dateFormatPattern = context?.getString(R.string.date_format)  // 포멧팅 형식 변경
            val formatter = DateTimeFormatter.ofPattern(dateFormatPattern, Locale.ENGLISH)
            binding.txtMonth.text = formatter.format(it.yearMonth)
            viewModel.loadEvents(it.yearMonth)
        }

        // 바인더 연결 (ViewModel의 상태로 View 구성)
        calendarView.dayBinder = CalendarDayBinder(
            getSelectedDate = { viewModel.selectedDate.value },
            getEvents = { viewModel.eventDates.value ?: emptySet() },
            onDateSelected = { viewModel.selectDate(it) }
        )
    }

    private fun observeViewModel() {
        viewModel.selectedDate.observe(viewLifecycleOwner) {
            calendarView.notifyCalendarChanged()
        }

        viewModel.eventDates.observe(viewLifecycleOwner) {
            calendarView.notifyCalendarChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
