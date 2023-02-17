package com.mespl.ismartkotlin.pta.ui.putaway

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mespl.ismartkotlin.databinding.FragmentPutawayBinding

class PutawayFragment : Fragment() {

    private var _binding: FragmentPutawayBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(PutawayViewModel::class.java)

        _binding = FragmentPutawayBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textPutaway
        galleryViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}