package com.example.task2.javafolder.viewviewmodel.fragments;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task2.R;
import com.example.task2.databinding.ItemBenchmarkBinding;
import com.example.task2.javafolder.model.Benchmark;
import com.example.task2.javafolder.model.StateOfCalculation;


public class BenchmarksAdapter extends ListAdapter<Benchmark, BenchmarksAdapter.BenchmarkViewHolder> {

    public BenchmarksAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Benchmark> DIFF_CALLBACK = new DiffUtil.ItemCallback<>() {
        @Override
        public boolean areItemsTheSame(@NonNull Benchmark oldItem, @NonNull Benchmark newItem) {
            return oldItem.getName().equals(newItem.getName());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Benchmark oldItem, @NonNull Benchmark newItem) {
            return oldItem.equals(newItem);
        }
    };

    @NonNull
    @Override
    public BenchmarkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BenchmarkViewHolder(ItemBenchmarkBinding.inflate
                (LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BenchmarkViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    protected static class BenchmarkViewHolder extends RecyclerView.ViewHolder {
        ItemBenchmarkBinding binding;

        public BenchmarkViewHolder(@NonNull ItemBenchmarkBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bind(Benchmark benchmark) {
            String benchmarksName = binding.getRoot().getContext().getString(benchmark.getName().nameResourceId);
            String benchmarksTimeOfCalculation = benchmark.getRepresentationOfCalculationsTime();
            binding.benchmarksText.setText(binding.getRoot().getContext().getString
                    (R.string.benchmarks_text_representation, benchmarksName, benchmarksTimeOfCalculation));

            if (benchmark.getState().equals(StateOfCalculation.CALCULATING)) {
                binding.progressCircular.setVisibility(View.VISIBLE);
            } else {
                binding.progressCircular.setVisibility(View.INVISIBLE);
            }
        }
    }
}
