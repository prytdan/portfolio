package com.example.task2.javafolder.viewviewmodel.fragments.parentfragment.collections;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.task2.javafolder.viewviewmodel.fragments.parentfragment.ParentFragment;
import com.example.task2.javafolder.viewviewmodel.fragments.parentfragment.ParentViewModel;

public class CollectionsFragment extends ParentFragment {
    private CollectionsViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(CollectionsViewModel.class);
    }

    @Override
    public int getSpanCount() {
        return 3;
    }

    @Override
    public ParentViewModel determineViewModelProvider() {
        return viewModel;
    }
}