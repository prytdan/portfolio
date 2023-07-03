package com.example.task2.kotlinfolder.viewviewmodel.fragments.parentfragment.maps

import com.example.task2.kotlinfolder.viewviewmodel.fragments.BenchmarkAdapter
import com.example.task2.kotlinfolder.viewviewmodel.fragments.parentfragment.ParentFragment
import com.example.task2.kotlinfolder.viewviewmodel.fragments.parentfragment.ParentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapsFragment : ParentFragment<MapsViewModel>(BenchmarkAdapter()) {

    override val viewModel: MapsViewModel by viewModel()

    override val spanCount = 2
}