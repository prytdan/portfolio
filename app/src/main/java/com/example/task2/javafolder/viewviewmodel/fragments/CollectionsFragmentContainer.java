package com.example.task2.javafolder.viewviewmodel.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.task2.R;
import com.example.task2.databinding.FragmentCollectionsContainerBinding;

public class CollectionsFragmentContainer extends Fragment {
    FragmentCollectionsContainerBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCollectionsContainerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}