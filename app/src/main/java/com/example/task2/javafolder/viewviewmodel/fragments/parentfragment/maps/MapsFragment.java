package com.example.task2.javafolder.viewviewmodel.fragments.parentfragment.maps;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.task2.javafolder.viewviewmodel.fragments.parentfragment.ParentFragment;
import com.example.task2.javafolder.viewviewmodel.fragments.parentfragment.ParentViewModel;

public class MapsFragment extends ParentFragment {
    MapsViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MapsViewModel.class);
    }

    @Override
    public int getSpanCount() {
        return 2;
    }

    @Override
    public ParentViewModel determineViewModelProvider() {
        return viewModel;
    }
}
