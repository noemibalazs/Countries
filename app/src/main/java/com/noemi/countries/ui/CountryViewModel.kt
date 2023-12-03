package com.noemi.countries.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.noemi.countries.domain.CountryRepository
import com.noemi.countries.model.Country
import com.noemi.countries.model.DetailedCountry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(private val countryRepository: CountryRepository) : ViewModel() {

    private val _countriesState = MutableStateFlow(CountriesState())
    val countriesState = _countriesState.asStateFlow()

    private val _countryState = MutableStateFlow(CountryState())
    val countryState = _countryState.asStateFlow()

    fun initialiseCountries(){
        viewModelScope.launch {
            _countriesState.update { it.copy(isLoading = true) }
            _countriesState.update {
                it.copy(
                    isLoading = false,
                    countries = countryRepository.getCountries()
                )
            }
        }
    }

    fun getSelectedCountry(code: String) {
        viewModelScope.launch {
            _countryState.update { it.copy(isLoading = true) }
            _countryState.update {
                it.copy(
                    isLoading = false,
                    selectedCountry = countryRepository.getCountry(code)
                )
            }
        }
    }

    fun dismissCountry() {
        viewModelScope.launch {
            _countryState.update {
                it.copy(selectedCountry = null)
            }
        }
    }

    data class CountryState(
        val isLoading: Boolean = false,
        val selectedCountry: DetailedCountry? = null
    )

    data class CountriesState(
        val isLoading: Boolean = false,
        val countries: List<Country> = emptyList()
    )

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}