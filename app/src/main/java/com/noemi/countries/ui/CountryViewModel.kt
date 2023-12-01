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
class CountryViewModel @Inject constructor(private val countryRepository: CountryRepository) :
    ViewModel() {

    private val _state = MutableStateFlow(CountriesState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            _state.update {
                it.copy(
                    isLoading = false,
                    countries = countryRepository.getCountries()
                )
            }
        }
    }

    fun getSelectedCountry(code: String) =
        viewModelScope.launch {
            _state.update {
                it.copy(
                    selectedCountry = countryRepository.getCountry(code)
                )
            }
        }


    fun dismissCountry() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    selectedCountry = null
                )
            }
        }
    }

    data class CountriesState(
        val isLoading: Boolean = false,
        val countries: List<Country> = emptyList(),
        val selectedCountry: DetailedCountry? = null
    )

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}