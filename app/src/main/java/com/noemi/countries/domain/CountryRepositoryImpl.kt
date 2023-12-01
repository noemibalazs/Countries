package com.noemi.countries.domain

import com.noemi.countries.datasource.CountryClient
import com.noemi.countries.model.Country
import com.noemi.countries.model.DetailedCountry
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(private val countryClient: CountryClient) : CountryRepository {

    override suspend fun getCountries(): List<Country> = countryClient.getCountries()

    override suspend fun getCountry(code: String): DetailedCountry? = countryClient.getCountry(code)
}