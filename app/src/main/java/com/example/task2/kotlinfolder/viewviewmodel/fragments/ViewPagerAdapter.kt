package com.example.task2.kotlinfolder.viewviewmodel.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import java.lang.IllegalArgumentException

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CollectionsFragmentContainer()
            1 -> MapsFragmentContainer()
            else -> throw IllegalArgumentException("Invalid position of Fragment")
        }
    }
}