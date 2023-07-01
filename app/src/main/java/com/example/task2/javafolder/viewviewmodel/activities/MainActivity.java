package com.example.task2.javafolder.viewviewmodel.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;

import com.example.task2.R;
import com.example.task2.databinding.ActivityMainBinding;
import com.example.task2.javafolder.viewviewmodel.fragments.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayoutMediator;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private TabLayoutMediator tabLayoutMediator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupView();
        setUpControls();
    }


    private void setupView() {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.viewPager.setAdapter(new ViewPagerAdapter(this));
        removeKeyboardWhenFragmentSwiped();
    }

    private void setUpControls() {
        tabLayoutMediator = new TabLayoutMediator(binding.tabLayout, binding.viewPager, (tab, position) -> {
            if (position == 0) {
                tab.setText(R.string.collections);
            } else if (position == 1) {
                tab.setText(R.string.maps);
            } else {
                throw new IllegalArgumentException("Invalid position of Fragment");
            }
        });
        tabLayoutMediator.attach();
    }

    private void removeKeyboardWhenFragmentSwiped() {
        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (inputMethodManager != null) {
                    inputMethodManager.hideSoftInputFromWindow(binding.viewPager.getWindowToken(), 0);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tabLayoutMediator.detach();
    }
}