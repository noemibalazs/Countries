package com.noemi.countries

import com.apollographql.apollo3.ApolloClient
import com.noemi.countries.datasource.ApolloCountryClient
import com.noemi.countries.domain.CountryRepository
import com.noemi.countries.domain.CountryRepositoryImpl
import com.noemi.countries.utils.BASE_URL
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class CountryRepositoryImplTest {

    private lateinit var countryRepository: CountryRepository

    private val countryCode = "CH"
    private val country = "Switzerland"
    private val capital = "Bern"

    @Before
    fun setUp() {
        val apolloClient = ApolloClient.Builder().serverUrl(BASE_URL).build()
        countryRepository = CountryRepositoryImpl(ApolloCountryClient(apolloClient))
    }

    @Test
    fun `test get countries and should be not null`() {
        runTest {
            val result = countryRepository.getCountries()
            result.shouldNotBe(null)
        }
    }

    @Test
    fun `test get country and should be null`() {
        runTest {
            val result = countryRepository.getCountry("")
            result?.shouldBe(null)
        }
    }

    @Test
    fun `test get country and should be Hungary`() {
        runTest {
            val result = countryRepository.getCountry(countryCode)
            result?.capital shouldBe capital
            result?.name shouldBe country
        }
    }
}