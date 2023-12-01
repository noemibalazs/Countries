package com.noemi.countries.datasource

import com.noemi.countries.model.Country
import com.noemi.countries.model.DetailedCountry

interface CountryClient {

    suspend fun getCountries(): List<Country>

    suspend fun getCountry(code: String): DetailedCountry?
}