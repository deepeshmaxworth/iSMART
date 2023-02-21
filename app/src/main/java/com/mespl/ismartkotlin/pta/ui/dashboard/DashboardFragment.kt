package com.mespl.ismartkotlin.pta.ui.dashboard

import android.R
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mespl.ismartkotlin.databinding.FragmentDashboardBinding


class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
//
//        val filter = IntentFilter()
//        filter.addCategory(Intent.CATEGORY_DEFAULT)
//        filter.addAction(resources.getString(R.string.activity_intent_filter_action))
//        registerReceiver(myBroadcastReceiver, filter)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}