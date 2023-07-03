package com.example.task2.kotlinfolder.viewviewmodel.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.task2.kotlinfolder.model.ErrorName
import com.example.task2.kotlinfolder.repo.Repository
import com.example.task2.kotlinfolder.repo.RepositoryImpl

abstract class BaseViewModel(protected val repository: Repository) : ViewModel() {
    private val presenceOfErrorInUsersInput: MutableLiveData<ErrorName> = MutableLiveData()

    internal val presenceOfErrorInUsersInputLiveData:
            LiveData<ErrorName> = presenceOfErrorInUsersInput

    private var isAlreadyInitialized = false

    fun attachView() {
        if (!isAlreadyInitialized) {
            isAlreadyInitialized = true
            onFirstAttach()
        }
    }

    protected open fun onFirstAttach() {}

    fun onEditTextChanged(usersInput: String) {
        presenceOfErrorInUsersInput.value = validateUsersInput(usersInput)
    }

    fun getUsersInputForTextInput(): String {
        return repository.usersInput.toString()
    }


    private fun validateUsersInput(input: String): ErrorName {
        if (input.isEmpty()) return ErrorName.EMPTY_INPUT
        return try {
            val inputInteger = input.toInt()
            if (inputInteger > 0) {
                repository.usersInput = inputInteger
                ErrorName.NO_ERROR
            } else {
                ErrorName.NEGATIVE_NUMBERS_INPUT
            }
        } catch (numberFormatException: NumberFormatException) {
            ErrorName.INVALID_INPUT
        }
    }
}