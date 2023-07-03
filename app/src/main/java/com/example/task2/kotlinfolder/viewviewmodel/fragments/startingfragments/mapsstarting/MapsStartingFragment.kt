package com.example.task2.kotlinfolder.viewviewmodel.fragments.startingfragments.mapsstarting

import com.example.task2.R
import com.example.task2.kotlinfolder.viewviewmodel.fragments.BaseViewModel
import com.example.task2.kotlinfolder.viewviewmodel.fragments.startingfragments.ParentStartingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapsStartingFragment : ParentStartingFragment() {

    override val viewModel: MapsStartingViewModel by viewModel()

    override val nextFragmentId: Int get() = R.id.action_mapsStartingFragment_to_fragmentWithMapsBenchmarks

    override val stringIdForTextView: Int get() = R.string.enter_map_size

    override val stringIdForEditTextHint: Int get() = R.string.edit_text_hint_map_size
}