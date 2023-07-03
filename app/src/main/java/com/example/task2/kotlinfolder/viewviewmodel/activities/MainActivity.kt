package com.example.task2.kotlinfolder.viewviewmodel.activities

import android.content.ClipboardManager
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.task2.R
import com.example.task2.databinding.ActivityMainBinding
import com.example.task2.kotlinfolder.viewviewmodel.fragments.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    private val inputMethodManager: InputMethodManager by inject()
    private lateinit var binding: ActivityMainBinding
    private lateinit var tabLayoutMediator: TabLayoutMediator
    private lateinit var onPageChangeCallback: ViewPager2.OnPageChangeCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpView()
        setUpControls()
    }

    private fun setUpView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewPager.adapter = ViewPagerAdapter(this);
        removeKeyboardWhenFragmentSwiped()
    }

    private fun setUpControls() {
        tabLayoutMediator =
            TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
                when (position) {
                    0 -> tab.text = getString(R.string.collections)
                    1 -> tab.text = getString(R.string.maps)
                    else -> throw IllegalArgumentException("Invalid position of Fragment")
                }
            }
        tabLayoutMediator.attach()
    }

    private fun removeKeyboardWhenFragmentSwiped() {
        onPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                inputMethodManager.hideSoftInputFromWindow(binding.viewPager.windowToken, 0)
            }
        }
        binding.viewPager.registerOnPageChangeCallback(onPageChangeCallback)
    }

    override fun onDestroy() {
        super.onDestroy()
        tabLayoutMediator.detach()
        binding.viewPager.unregisterOnPageChangeCallback(onPageChangeCallback)
    }
}