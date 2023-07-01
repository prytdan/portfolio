package com.example.task2.javafolder.viewviewmodel.fragments.startingfragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.navigation.Navigation;

import com.example.task2.databinding.FragmentStartingBinding;
import com.example.task2.javafolder.viewviewmodel.fragments.BaseFragment;
import com.example.task2.javafolder.viewviewmodel.fragments.parentfragment.ParentViewModel;
import com.google.android.material.textfield.TextInputEditText;

import dagger.hilt.android.AndroidEntryPoint;

public abstract class ParentStartingFragment extends BaseFragment {

    private FragmentStartingBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentStartingBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setCalculationsButtonClicker();
        binding.textView.setText(stringIdForTextView());
        binding.textField.setHint(stringIdForEditTextHint());
    }

    public abstract int determineNextFragment();

    @StringRes
    public abstract int stringIdForTextView();

    @StringRes
    public abstract int stringIdForEditTextHint();

    @Override
    public final TextInputEditText getUsersInputInstance() {
        return binding.usersInput;
    }

    @Override
    public Button getButtonInstance() {
        return binding.button;
    }

    private void setCalculationsButtonClicker() {
        binding.button.setOnClickListener(view -> Navigation.findNavController(view).navigate(determineNextFragment()));
    }
}
