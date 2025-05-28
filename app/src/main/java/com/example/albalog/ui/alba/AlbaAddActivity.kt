package com.example.albalog.ui.alba

import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.albalog.R
import com.example.albalog.databinding.ActivityAlbaAddBinding
import java.util.*

class AlbaAddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlbaAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbaAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupTimePickers()
        setupSubmitButton()

        val viewModel: AlbaViewModel by viewModels()

        binding.textStartTime.setOnClickListener {
            showTimePicker(this) { time ->
                binding.textStartTime.text = time
            }
        }

        binding.textEndTime.setOnClickListener {
            showTimePicker(this) { time ->
                binding.textEndTime.text = time
            }
        }

        binding.btnSubmit.setOnClickListener {
            val title = binding.editTitle.text.toString()

            val days = getCheckedDays()

//            viewModel.insertAlba(title, start, end, days)
            Toast.makeText(this, "DB 저장 완료", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun setupTimePickers() {

    }

    private fun showTimePicker(context: Context, onTimeSelected: (String) -> Unit) {
        val now = Calendar.getInstance()
        val hour = now.get(Calendar.HOUR_OF_DAY)
        val minute = now.get(Calendar.MINUTE)

        TimePickerDialog(
            context,
            { _, selectedHour, selectedMinute ->
                val time = String.format("%02d:%02d", selectedHour, selectedMinute)
                onTimeSelected(time)
            },
            hour,
            minute,
            false // AM/PM 사용
        ).show()
    }

    private fun setupSubmitButton() {
        binding.btnSubmit.setOnClickListener {
            val title = binding.editTitle.text.toString().trim()

            val repeatDays = getCheckedDays()

            Toast.makeText(this, "알바 등록 완료", Toast.LENGTH_SHORT).show()
            finish() // 등록 완료 후 종료
        }
    }

    private fun getCheckedDays(): List<String> {
        val map = mapOf(
            getString(R.string.day_sunday) to binding.checkSun,
            getString(R.string.day_monday) to binding.checkMon,
            getString(R.string.day_tuesday) to binding.checkTue,
            getString(R.string.day_wednesday) to binding.checkWed,
            getString(R.string.day_thursday) to binding.checkThu,
            getString(R.string.day_friday) to binding.checkFri,
            getString(R.string.day_saturday) to binding.checkSat
        )
        return map.filter { it.value.isChecked }.map { it.key }
    }
}
