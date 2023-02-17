package com.mespl.ismartkotlin.pta.ui.bagsadjustment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mespl.ismartkotlin.databinding.FragmentBagsadjustmentBinding
import com.mespl.ismartkotlin.pta.ui.dashboard.DashboardViewModel


class BagsAdjustmentFragment : Fragment() {

    private var _binding: FragmentBagsadjustmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentBagsadjustmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textBagsadjustment
        slideshowViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}