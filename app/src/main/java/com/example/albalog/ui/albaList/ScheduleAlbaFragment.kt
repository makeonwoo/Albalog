package com.example.albalog.ui.albaList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.albalog.R
import com.example.albalog.databinding.FragmentScheduleAlbaBinding

class ScheduleAlbaFragment : Fragment() {

    private var _binding: FragmentScheduleAlbaBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(ScheduleAlbaViewModel::class.java)

        _binding = FragmentScheduleAlbaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // 1번: 알바 등록
        val btnAlbaAdd = root.findViewById<View>(R.id.btnAlbaAdd)
        btnAlbaAdd.findViewById<TextView>(R.id.textButtonTitle).text = "알바 등록"

        // 2번: 알바 수정
        val btnAlbaEdit = root.findViewById<View>(R.id.btnAlbaEdit)
        btnAlbaEdit.findViewById<TextView>(R.id.textButtonTitle).text = "알바 수정"

        // 3번: 알바 현황
        val btnAlbaStatus = root.findViewById<View>(R.id.btnAlbaStatus)
        btnAlbaStatus.findViewById<TextView>(R.id.textButtonTitle).text = "알바 현황"

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}