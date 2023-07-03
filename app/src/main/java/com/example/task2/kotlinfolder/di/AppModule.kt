package com.example.task2.kotlinfolder.di

import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.task2.kotlinfolder.repo.Repository
import com.example.task2.kotlinfolder.viewviewmodel.fragments.BenchmarkAdapter
import com.example.task2.kotlinfolder.viewviewmodel.fragments.parentfragment.collections.CollectionsViewModel
import com.example.task2.kotlinfolder.viewviewmodel.fragments.parentfragment.maps.MapsViewModel
import com.example.task2.kotlinfolder.viewviewmodel.fragments.startingfragments.collectionsstarting.CollectionsStartingViewModel
import com.example.task2.kotlinfolder.viewviewmodel.fragments.startingfragments.mapsstarting.MapsStartingViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.util.Random

val appModule = module {
    single { Random() }
    single { androidContext().getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager }
    factory { BenchmarkAdapter() }
    viewModel { CollectionsStartingViewModel(repository = get<Repository>()) }
    viewModel { MapsStartingViewModel(repository = get<Repository>()) }
    viewModel { CollectionsViewModel(repository = get<Repository>()) }
    viewModel { MapsViewModel(repository = get<Repository>(), random = get()) }
}