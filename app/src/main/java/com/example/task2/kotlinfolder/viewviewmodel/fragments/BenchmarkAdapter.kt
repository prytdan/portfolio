package com.example.task2.kotlinfolder.viewviewmodel.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.task2.R
import com.example.task2.databinding.ItemBenchmarkBinding
import com.example.task2.kotlinfolder.model.Benchmark
import com.example.task2.kotlinfolder.model.StateOfCalculation
import com.example.task2.kotlinfolder.inflateBinding

class BenchmarkAdapter :
    ListAdapter<Benchmark, BenchmarkAdapter.BenchmarkViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BenchmarkViewHolder {
        return BenchmarkViewHolder(
            parent.inflateBinding(ItemBenchmarkBinding::inflate)
        )
    }

    override fun onBindViewHolder(holder: BenchmarkViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class DiffCallback : DiffUtil.ItemCallback<Benchmark>() {
        override fun areItemsTheSame(oldItem: Benchmark, newItem: Benchmark): Boolean =
            oldItem.name == newItem.name


        override fun areContentsTheSame(oldItem: Benchmark, newItem: Benchmark): Boolean =
            oldItem == newItem
    }


    class BenchmarkViewHolder(private val binding: ItemBenchmarkBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(benchmark: Benchmark) {
            binding.apply {
                val benchmarkName = root.context.getString(benchmark.name.nameResourceId)
                benchmarksText.text = root.context.getString(
                    R.string.benchmarks_text_representation,
                    benchmarkName,
                    benchmark.timeOfCalculationToString
                )
                progressCircular.visibility =
                    if (benchmark.stateOfCalculation == StateOfCalculation.CALCULATING) View.VISIBLE else View.INVISIBLE
            }
        }
    }

}

