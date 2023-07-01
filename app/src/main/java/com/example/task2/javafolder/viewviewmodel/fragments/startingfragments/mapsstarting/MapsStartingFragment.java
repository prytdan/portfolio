package com.example.task2.javafolder.viewviewmodel.fragments.startingfragments.mapsstarting;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.lifecycle.ViewModelProvider;

import com.example.task2.R;
import com.example.task2.javafolder.viewviewmodel.fragments.BaseViewModel;
import com.example.task2.javafolder.viewviewmodel.fragments.startingfragments.ParentStartingFragment;
import com.example.task2.javafolder.viewviewmodel.fragments.startingfragments.ParentStartingViewModel;
import com.example.task2.javafolder.viewviewmodel.fragments.startingfragments.collectionsstarting.CollectionsStartingViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MapsStartingFragment extends ParentStartingFragment {
    private MapsStartingViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MapsStartingViewModel.class);
    }

    @Override
    public BaseViewModel determineViewModelProvider() {
        return viewModel;
    }

    @Override
    public int determineNextFragment() {
        return R.id.action_mapsStartingFragment_to_fragmentWithMapsBenchmarks;
    }

    @Override
    public int stringIdForTextView() {
        return R.string.enter_map_size;
    }

    @Override
    public int stringIdForEditTextHint() {
        return R.string.edit_text_hint_map_size;
    }
}