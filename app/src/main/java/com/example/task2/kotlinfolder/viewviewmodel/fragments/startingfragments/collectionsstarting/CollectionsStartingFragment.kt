package com.example.task2.kotlinfolder.viewviewmodel.fragments.startingfragments.collectionsstarting

import com.example.task2.R
import com.example.task2.kotlinfolder.viewviewmodel.fragments.BaseViewModel
import com.example.task2.kotlinfolder.viewviewmodel.fragments.startingfragments.ParentStartingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class CollectionsStartingFragment : ParentStartingFragment() {

    override val viewModel: CollectionsStartingViewModel by viewModel()

    override val nextFragmentId: Int get() = R.id.action_collectionsStartingFragment_to_fragmentWithCollectionsBenchmarks

    override val stringIdForTextView: Int get() = R.string.enter_collection_size

    override val stringIdForEditTextHint: Int get() = R.string.edit_text_hint_collection_size
}