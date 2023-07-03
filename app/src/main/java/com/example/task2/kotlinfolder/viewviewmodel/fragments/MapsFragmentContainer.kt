package com.example.task2.kotlinfolder.viewviewmodel.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.task2.R
import com.example.task2.databinding.FragmentCollectionsContainerBinding
import com.example.task2.databinding.FragmentMapsContainerBinding

class MapsFragmentContainer : Fragment() {
    private lateinit var binding: FragmentMapsContainerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapsContainerBinding.inflate(inflater, container, false)
        return binding.root
    }
}