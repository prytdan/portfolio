package com.example.task2.javafolder.viewviewmodel.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import com.example.task2.R;
import com.google.android.material.textfield.TextInputEditText;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public abstract class BaseFragment extends Fragment {

    private BaseViewModel baseViewModel;

    public abstract TextInputEditText getUsersInputInstance();

    public abstract Button getButtonInstance();

    public abstract BaseViewModel determineViewModelProvider();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        baseViewModel = determineViewModelProvider();
        setUpControls();
        subscribeToViewModel();
    }

    private void subscribeToErrorObservable() {
        baseViewModel.presenceOfErrorInUsersInputLiveData.observe(getViewLifecycleOwner(), errorName -> {
            switch (errorName) {
                case INVALID_INPUT:
                    getButtonInstance().setEnabled(false);
                    getUsersInputInstance().setError(getString(R.string.error_text_invalid_input));
                    return;
                case NEGATIVE_NUMBERS_INPUT:
                    getButtonInstance().setEnabled(false);
                    getUsersInputInstance().setError(getString(R.string.error_text_negative_numbers_input));
                    return;
                case EMPTY_INPUT:
                    getButtonInstance().setEnabled(false);
                    getUsersInputInstance().setError(getString(R.string.error_text_empty_input));
                    return;
                case NO_ERROR:
                    getButtonInstance().setEnabled(true);
                    return;
                default:
                    return;
            }
        });
    }

    private void addTextWatcherToEditText() {
        getUsersInputInstance().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                baseViewModel.onEditTextChanged(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setUpControls() {
        addTextWatcherToEditText();
    }

    private void subscribeToViewModel() {
        subscribeToErrorObservable();
    }

}
