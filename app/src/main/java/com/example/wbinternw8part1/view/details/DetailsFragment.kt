package com.example.wbinternw8part1.view.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.api.load
import com.example.wbinternw8part1.R
import com.example.wbinternw8part1.databinding.FragmentDetailsBinding
import com.example.wbinternw8part1.model.DataModel

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private lateinit var binding: FragmentDetailsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)
        renderData()
    }

    private fun renderData() {
        val data = arguments?.getParcelable<DataModel>(KEY)
        with(binding) {
            data?.let {
                image.load("https://api.opendota.com${data.img}")
                localizedName.text = data.localized_name
                attackType.text = data.attack_type
                baseHealth.text = data.base_health.toString()
                baseMana.text = data.base_mana.toString()
                baseMr.text = data.base_mr.toString()
                baseAttackMin.text = data.base_attack_min.toString()
                baseAttackMax.text = data.base_attack_max.toString()
            }
        }
    }

    companion object {
        private const val KEY = "details"

        fun newInstance(dataModel: DataModel) = DetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable(KEY, dataModel)
            }
        }
    }
}