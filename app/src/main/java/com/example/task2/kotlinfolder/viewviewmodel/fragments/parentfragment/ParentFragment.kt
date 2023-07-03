package com.example.task2.kotlinfolder.viewviewmodel.fragments.parentfragment

import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import com.example.task2.databinding.FragmentWithBenchmarksBinding
import com.example.task2.kotlinfolder.model.StateOfCalculation
import com.example.task2.kotlinfolder.viewviewmodel.fragments.BaseFragment
import com.example.task2.kotlinfolder.viewviewmodel.fragments.BenchmarkAdapter
import com.google.android.material.textfield.TextInputEditText

abstract class ParentFragment<VM : ParentViewModel>(private val benchmarkAdapter: BenchmarkAdapter) :
    BaseFragment<FragmentWithBenchmarksBinding, VM>() {

    override fun getViewBinding() = FragmentWithBenchmarksBinding.inflate(layoutInflater)

    override fun initViews() {
        super.initViews()
        setUpView()
        setUpControls()
        subscribeToViewModel()
    }

    abstract val spanCount: Int

    override val usersInputEditText: TextInputEditText get() = binding.usersInput

    override val calculateButton: Button get() = binding.startStopButton


    private fun setUpView() {
        setupRecyclerViewElements()
        setNumberToEditText()
    }

    private fun setUpControls() {
        binding.startStopButton.setOnClickListener {
            when (binding.startStopButton.isChecked) {
                true -> viewModel.startCalculations()
                false -> viewModel.stopCalculations()
            }
        }
    }

    private fun subscribeToViewModel() {
        viewModel.liveDataForStateOfCalculation.observe(viewLifecycleOwner) {
            binding.startStopButton.isChecked = it != StateOfCalculation.TERMINATED
        }
        viewModel.liveDataForListOfBenchmarks.observe(viewLifecycleOwner) {
            this.benchmarkAdapter.submitList(ArrayList(it))
        }
    }

    private fun setupRecyclerViewElements() {
        binding.recyclerViewForBenchmarks.layoutManager = GridLayoutManager(context, spanCount)
        binding.recyclerViewForBenchmarks.setHasFixedSize(true)
        binding.recyclerViewForBenchmarks.adapter = this.benchmarkAdapter
    }

    private fun setNumberToEditText() {
        binding.usersInput.setText(viewModel.getUsersInputForTextInput())
    }

}