package com.example.task2.javafolder.viewviewmodel.fragments.parentfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.task2.databinding.FragmentWithBenchmarksBinding;
import com.example.task2.javafolder.model.StateOfCalculation;
import com.example.task2.javafolder.viewviewmodel.fragments.BaseFragment;
import com.example.task2.javafolder.viewviewmodel.fragments.BenchmarksAdapter;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public abstract class ParentFragment extends BaseFragment {

    protected FragmentWithBenchmarksBinding binding;

    private BenchmarksAdapter benchmarksAdapter;

    public abstract int getSpanCount();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentWithBenchmarksBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpView();
        setupControls();
        subscribeToViewModel();
    }

    @Override
    public abstract ParentViewModel determineViewModelProvider();



    @Override
    public TextInputEditText getUsersInputInstance() {
        return binding.usersInput;
    }

    @Override
    public Button getButtonInstance() {
        return binding.startStopButton;
    }

    private void setUpView() {
        benchmarksAdapter = new BenchmarksAdapter();
        setupRecyclerViewElements();
        determineViewModelProvider().setBenchmarks();
        setNumberToEditText();
    }

    private void setupControls() {
        binding.startStopButton.setOnClickListener(view -> {
            if (binding.startStopButton.isChecked()) {
                determineViewModelProvider().startCalculations();
            } else {
                determineViewModelProvider().stopCalculations();
            }
        });
    }

    private void subscribeToViewModel() {
        determineViewModelProvider().liveDataForStateOfCalculation.observe(getViewLifecycleOwner(), stateOfCalculation ->
                binding.startStopButton.setChecked(stateOfCalculation != StateOfCalculation.TERMINATED));
        determineViewModelProvider().liveDataForListOfBenchmarks.observe(getViewLifecycleOwner(), list ->
                benchmarksAdapter.submitList(new ArrayList<>(list)));
    }

    private void setupRecyclerViewElements() {
        binding.recyclerViewForBenchmarks.setLayoutManager
                (new GridLayoutManager(getContext(), getSpanCount()));
        binding.recyclerViewForBenchmarks.setHasFixedSize(true);
        binding.recyclerViewForBenchmarks.setAdapter(benchmarksAdapter);
    }

    private void setNumberToEditText() {
        binding.usersInput.setText(determineViewModelProvider().getUsersInputForTextInput());
    }
}
