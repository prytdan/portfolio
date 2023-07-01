package com.example.task2.javafolder.viewviewmodel.fragments;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.task2.javafolder.model.ErrorName;
import com.example.task2.javafolder.repo.RepositoryImpl;

import javax.inject.Inject;

public abstract class BaseViewModel extends ViewModel {

    private final MutableLiveData<ErrorName> presenceOfErrorInUsersInput = new MutableLiveData<>();

    public final LiveData<ErrorName> presenceOfErrorInUsersInputLiveData = presenceOfErrorInUsersInput;

    @Inject
    public RepositoryImpl repository;

    public String getUsersInputForTextInput() {
        return String.valueOf(repository.getUsersInput());
    }

    protected void onEditTextChanged(String usersInputString) {
        presenceOfErrorInUsersInput.setValue(validateUsersInput(usersInputString));
    }

    protected ErrorName validateUsersInput(String input) {
        if (input.isEmpty()) {
            return ErrorName.EMPTY_INPUT;
        }
        try {
            int inputInteger = Integer.parseInt(input);
            if (inputInteger > 0) {
                repository.setUsersInput(inputInteger);
                return ErrorName.NO_ERROR;
            } else {
                return ErrorName.NEGATIVE_NUMBERS_INPUT;
            }
        } catch (NumberFormatException numberFormatException) {
            return ErrorName.INVALID_INPUT;
        }
    }

}
