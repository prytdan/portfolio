package com.example.task2.kotlinfolder.viewviewmodel.fragments.startingfragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.StringRes
import androidx.navigation.Navigation
import com.example.task2.databinding.FragmentStartingBinding
import com.example.task2.kotlinfolder.viewviewmodel.fragments.BaseFragment
import com.google.android.material.textfield.TextInputEditText

abstract class ParentStartingFragment :
    BaseFragment<FragmentStartingBinding, ParentStartingViewModel>() {

    override fun getViewBinding() = FragmentStartingBinding.inflate(layoutInflater)

    override fun initViews() {
        super.initViews()
        setCalculationsButtonClicker()
        binding.textView.setText(stringIdForTextView)
        binding.textField.setHint(stringIdForEditTextHint)
    }

    abstract val nextFragmentId: Int

    @get:StringRes
    abstract val stringIdForTextView: Int

    @get:StringRes
    abstract val stringIdForEditTextHint: Int

    override val usersInputEditText: TextInputEditText get() = binding.usersInput

    override val calculateButton: Button get() = binding.button

    private fun setCalculationsButtonClicker() {
        binding.button.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(nextFragmentId)
        }
    }
}
