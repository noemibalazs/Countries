package com.noemi.countries.ui

import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.noemi.countries.R
import com.noemi.countries.model.Country

@BindingAdapter("icon")
fun bindingCountryIcon(view: ImageView, imageUrl: String?) {
    Glide.with(view.context)
        .load(imageUrl)
        .circleCrop()
        .placeholder(R.drawable.ic_hu)
        .error(R.drawable.ic_hu)
        .into(view)
}

@BindingAdapter("progressBarState")
fun bindProgressBarState(progressBar: CircularProgressIndicator, isLoading: Boolean?) {
    progressBar.isVisible = isLoading ?: false
}

@BindingAdapter("countries")
fun bindUsers(recycleView: RecyclerView, users: List<Country>?) {
    (recycleView.adapter as CountriesAdapter).submitList(users)
}