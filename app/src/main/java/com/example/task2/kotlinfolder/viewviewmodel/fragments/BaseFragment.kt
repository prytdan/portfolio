package com.example.task2.kotlinfolder.viewviewmodel.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.task2.R
import com.example.task2.kotlinfolder.model.ErrorName
import com.google.android.material.textfield.TextInputEditText

abstract class BaseFragment<VB : ViewBinding, VM : BaseViewModel> : Fragment() {

    abstract val viewModel: VM

    private var viewBinding: VB? = null

    val binding get() = viewBinding!!

    abstract fun getViewBinding(): VB

    abstract val usersInputEditText: TextInputEditText

    abstract val calculateButton: Button


    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = getViewBinding()
        return viewBinding?.root
    }

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        viewModel.attachView()
    }

    protected open fun initViews() {
        setupControls()
        subscribeToViewModel()
    }


    private fun setupControls() {
        usersInputEditText.addTextChangedListener {
            this.viewModel.onEditTextChanged(it.toString())
        }
    }

    private fun subscribeToViewModel() {
        this.viewModel.presenceOfErrorInUsersInputLiveData.observe(
            viewLifecycleOwner,
            this::handleErrorName
        )
    }

    private fun handleErrorName(errorName: ErrorName) {
        when (errorName) {
            ErrorName.EMPTY_INPUT -> {
                calculateButton.isEnabled = false
                usersInputEditText.error = getString(R.string.error_text_empty_input)
            }

            ErrorName.NEGATIVE_NUMBERS_INPUT -> {
                calculateButton.isEnabled = false
                usersInputEditText.error =
                    getString(R.string.error_text_negative_numbers_input)
            }

            ErrorName.INVALID_INPUT -> {
                calculateButton.isEnabled = false
                usersInputEditText.error = getString(R.string.edit_text_invalid_input)
            }

            ErrorName.NO_ERROR -> calculateButton.isEnabled = true
        }
    }


    override fun onDestroyView() {
        viewBinding = null
        super.onDestroyView()
    }
}