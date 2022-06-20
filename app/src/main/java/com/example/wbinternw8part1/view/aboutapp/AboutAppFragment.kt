package com.example.wbinternw8part1.view.aboutapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wbinternw8part1.R
import com.example.wbinternw8part1.databinding.FragmentAboutAppBinding
import com.example.wbinternw8part1.databinding.FragmentDetailsBinding

class AboutAppFragment : Fragment(R.layout.fragment_about_app) {

    private lateinit var binding: FragmentAboutAppBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAboutAppBinding.bind(view)
        binding.alphaContainer.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.accept.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
}