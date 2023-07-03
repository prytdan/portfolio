package com.example.task2.kotlinfolder.viewviewmodel.fragments.parentfragment.collections

import com.example.task2.kotlinfolder.viewviewmodel.fragments.BenchmarkAdapter
import com.example.task2.kotlinfolder.viewviewmodel.fragments.parentfragment.ParentFragment
import com.example.task2.kotlinfolder.viewviewmodel.fragments.parentfragment.ParentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CollectionsFragment() : ParentFragment<CollectionsViewModel>(BenchmarkAdapter()) {

    override val viewModel: CollectionsViewModel by viewModel()

    override val spanCount = 3

}