package com.noemi.countries

import com.apollographql.apollo3.ApolloClient
import com.noemi.countries.datasource.ApolloCountryClient
import com.noemi.countries.datasource.CountryClient
import com.noemi.countries.utils.BASE_URL
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.test.runTest
import io.kotest.matchers.shouldNotBe
import org.junit.Before
import org.junit.Test

class ApolloCountryClientTest {

    private lateinit var countryClient: CountryClient
    private val countryCode = "HU"
    private val country = "Hungary"
    private val capital = "Budapest"

    @Before
    fun setUp() {
        val apolloClient = ApolloClient.Builder().serverUrl(BASE_URL).build()
        countryClient = ApolloCountryClient(apolloClient)
    }

    @Test
    fun `test get countries and should be not null`() {
        runTest {

            val result = countryClient.getCountries()
            result.shouldNotBe(null)
        }
    }

    @Test
    fun `test get country and should be Hungary`() {
        runTest {
            val result = countryClient.getCountry(countryCode)
            result?.capital shouldBe capital
            result?.name shouldBe country
        }
    }

    @Test
    fun `test get country and should be null`() {
        runTest {
            val result = countryClient.getCountry("")
            result?.shouldBe(null)
        }
    }
}