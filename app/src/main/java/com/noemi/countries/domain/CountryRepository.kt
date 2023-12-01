package com.noemi.countries.domain

import com.noemi.countries.model.Country
import com.noemi.countries.model.DetailedCountry

interface CountryRepository {

    suspend fun getCountries(): List<Country>

    suspend fun getCountry(code: String): DetailedCountry?

}