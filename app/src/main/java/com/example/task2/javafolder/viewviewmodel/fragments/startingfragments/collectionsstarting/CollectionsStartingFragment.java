package com.example.task2.javafolder.viewviewmodel.fragments.startingfragments.collectionsstarting;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.task2.R;
import com.example.task2.javafolder.viewviewmodel.fragments.BaseViewModel;
import com.example.task2.javafolder.viewviewmodel.fragments.parentfragment.collections.CollectionsViewModel;
import com.example.task2.javafolder.viewviewmodel.fragments.startingfragments.ParentStartingFragment;
import com.example.task2.javafolder.viewviewmodel.fragments.startingfragments.ParentStartingViewModel;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CollectionsStartingFragment extends ParentStartingFragment {
    private CollectionsStartingViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(CollectionsStartingViewModel.class);
    }

    @Override
    public BaseViewModel determineViewModelProvider() {
        return viewModel;
    }

    @Override
    public int determineNextFragment() {
        return R.id.action_collectionsStartingFragment_to_fragmentWithCollectionsBenchmarks;
    }

    @Override
    public int stringIdForTextView() {
        return R.string.enter_collection_size;
    }

    @Override
    public int stringIdForEditTextHint() {
        return R.string.edit_text_hint_collection_size;
    }
}