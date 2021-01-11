package com.example.skeletonapp.features.rates

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.skeletonapp.App
import com.example.skeletonapp.R
import com.example.skeletonapp.core.view.BaseFragment
import com.example.skeletonapp.databinding.FragmentRatesBinding
import com.example.skeletonapp.features.rates.adapter.RatesAdapter

class RatesFragment : BaseFragment<RatesViewModel>(R.layout.fragment_rates) {

    private lateinit var binding: FragmentRatesBinding
    private lateinit var listAdapter: RatesAdapter

    override fun onAttach(context: Context) {
        (activity?.application as App).appComponent.ratesComponent().create().inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRatesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        observeProgress()
        observeErrors()
        observeRates()
    }

    private fun initRecyclerView() {
        listAdapter = RatesAdapter(
            onCurrencyRateClicked = { fromPosition ->
                viewModel.onRateClicked(fromPosition)
            },
            onCurrencyRateChanged = { rate ->
                viewModel.onRateChanged(rate)
            }
        )

        binding.ratesList.adapter = listAdapter
    }

    private fun observeRates() {
        viewModel.rates.observe(viewLifecycleOwner, { items ->
            listAdapter.submitList(items)
        })
    }

    private fun observeProgress() {
        viewModel.isLoading.observe(viewLifecycleOwner, { isLoading ->
            if (isLoading)
                binding.progress.visibility = View.VISIBLE
            else
                binding.progress.visibility = View.INVISIBLE
        })
    }

    private fun observeErrors() {
        viewModel.isError.observe(viewLifecycleOwner, { isError ->
            if (isError)
                binding.errorText.visibility = View.VISIBLE
            else
                binding.errorText.visibility = View.INVISIBLE
        })
    }
}