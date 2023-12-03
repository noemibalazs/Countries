package com.noemi.countries.ui

import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.noemi.countries.model.Country

@BindingAdapter("progressBarState")
fun bindProgressBarState(progressBar: CircularProgressIndicator, isLoading: Boolean?) {
    progressBar.isVisible = isLoading ?: false
}

@BindingAdapter("countries")
fun bindUsers(recycleView: RecyclerView, users: List<Country>?) {
    (recycleView.adapter as CountriesAdapter).submitList(users)
}

@BindingAdapter("isViewVisible")
fun bindProgressBarState(view: ViewGroup, isLoading: Boolean?) {
    view.isVisible = isLoading == false
}