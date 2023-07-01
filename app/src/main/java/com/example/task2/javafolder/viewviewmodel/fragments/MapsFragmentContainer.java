package com.example.task2.javafolder.viewviewmodel.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.task2.R;
import com.example.task2.databinding.FragmentCollectionsContainerBinding;
import com.example.task2.databinding.FragmentMapsContainerBinding;

public class MapsFragmentContainer extends Fragment {
    FragmentMapsContainerBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMapsContainerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}