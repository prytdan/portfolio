package com.example.task2.kotlinfolder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.viewbinding.ViewBinding

fun <T : ViewBinding> ViewGroup.inflateBinding(
    inflateFunc: (LayoutInflater, ViewGroup, Boolean) -> T
): T = inflateFunc(LayoutInflater.from(context), this, false)

fun TextView.setTextResource(@StringRes resource: Int) {
    text = context.getString(resource)
}

fun <T> MutableLiveData<T>.readOnly(): LiveData<T> = this

fun MutableLiveData<Unit>.call() {
    this.value = Unit
}
