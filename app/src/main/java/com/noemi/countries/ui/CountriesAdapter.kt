package com.noemi.countries.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.noemi.countries.databinding.ItemCountryBinding
import com.noemi.countries.model.Country

class CountriesAdapter(private val clickListener: CountryClickListener) : ListAdapter<Country, CountriesAdapter.CountryViewHolder>(CountryDifUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding: ItemCountryBinding =
            ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class CountryViewHolder(private val binding: ItemCountryBinding) :
        ViewHolder(binding.root) {

        fun bind(item: Country) {
            with(binding) {
                country = item
                listener = clickListener
                executePendingBindings()
            }
        }
    }

    object CountryDifUtil : DiffUtil.ItemCallback<Country>() {

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean =
            oldItem == newItem

        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean =
            oldItem.name == newItem.name
    }
}