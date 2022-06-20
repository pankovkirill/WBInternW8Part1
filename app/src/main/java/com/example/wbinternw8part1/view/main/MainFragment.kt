package com.example.wbinternw8part1.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wbinternw8part1.R
import com.example.wbinternw8part1.databinding.FragmentDetailsBinding
import com.example.wbinternw8part1.databinding.FragmentMainBinding
import com.example.wbinternw8part1.model.AppState
import com.example.wbinternw8part1.model.DataModel
import com.example.wbinternw8part1.view.details.DetailsFragment
import com.example.wbinternw8part1.view.main.adapter.MainAdapter
import com.example.wbinternw8part1.viewmodel.MainViewModel


class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding

    private val adapter: MainAdapter by lazy { MainAdapter(onListItemClickListener) }

    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(dataModel: DataModel) {
                parentFragmentManager
                    .beginTransaction()
                    .addToBackStack(null)
                    .add(R.id.container, DetailsFragment.newInstance(dataModel))
                    .commit()
            }
        }

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        initViewModel()
        initView()
    }

    private fun initView() {
        binding.mainFragmentRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.mainFragmentRecyclerView.adapter = adapter
    }

    private fun initViewModel() {
        viewModel.liveDataForViewToObserve.observe(viewLifecycleOwner) { renderData(it) }
        viewModel.getData()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {
                Toast.makeText(context, appState.error.message, Toast.LENGTH_SHORT).show()
            }
            is AppState.Loading -> {
                Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
            }
            is AppState.Success -> {
                appState.data?.let {
                    if (it.isEmpty())
                        Toast.makeText(context, "No data available", Toast.LENGTH_SHORT).show()
                    else
                        adapter.setData(it)
                }
            }
        }
    }
}