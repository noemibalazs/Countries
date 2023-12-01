package com.noemi.countries.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.noemi.countries.databinding.ActivityCountriesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountriesActivity : AppCompatActivity(), CountryClickListener {

    private val countryViewModel: CountryViewModel by viewModels()
    private val adapter: CountriesAdapter by lazy { CountriesAdapter(this) }

    private lateinit var viewBinding: ActivityCountriesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityCountriesBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        with(viewBinding) {
            viewModel = countryViewModel
            lifecycleOwner = this@CountriesActivity
            countriesRecycleView.adapter = adapter
        }
    }

    override fun onCountryClicked(code: String) {
        countryViewModel.getSelectedCountry(code)
        val country = countryViewModel.state.value.selectedCountry
        CountryDetailsDialog.getInstance(country = country)
            .show(supportFragmentManager, CountryDetailsDialog.TAG)
    }
}