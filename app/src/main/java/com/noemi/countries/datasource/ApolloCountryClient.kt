package com.noemi.countries.datasource

import com.apollographql.apollo3.ApolloClient
import com.noemi.countries.CountriesQuery
import com.noemi.countries.CountryQuery
import com.noemi.countries.model.Country
import com.noemi.countries.model.DetailedCountry
import com.noemi.countries.utils.toCountry
import com.noemi.countries.utils.toDetailedCountry
import javax.inject.Inject

class ApolloCountryClient @Inject constructor(private val apolloClient: ApolloClient) : CountryClient {

    override suspend fun getCountries(): List<Country> =
        apolloClient.query(CountriesQuery())
            .execute()
            .data?.countries?.map { it.toCountry() } ?: emptyList<Country>()


    override suspend fun getCountry(code: String): DetailedCountry? =
        apolloClient.query(CountryQuery(code))
            .execute()
            .data?.country?.toDetailedCountry()
}