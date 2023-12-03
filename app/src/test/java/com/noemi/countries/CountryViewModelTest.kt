package com.noemi.countries

import android.annotation.SuppressLint
import app.cash.turbine.test
import com.apollographql.apollo3.ApolloClient
import com.noemi.countries.datasource.ApolloCountryClient
import com.noemi.countries.domain.CountryRepositoryImpl
import com.noemi.countries.model.Country
import com.noemi.countries.ui.CountryViewModel
import com.noemi.countries.utils.BASE_URL
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before

import org.junit.Test
import org.mockito.Mockito.*

@Suppress("DEPRECATION")
@SuppressLint("CheckResult")
@ExperimentalCoroutinesApi
class CountryViewModelTest {

    private lateinit var viewModel: CountryViewModel

    private val countryCode = "HU"

    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        val apolloClient = ApolloClient.Builder().serverUrl(BASE_URL).build()
        val countryRepository = CountryRepositoryImpl(ApolloCountryClient(apolloClient))
        viewModel = CountryViewModel(countryRepository)
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test initialise countries and should pass`() {
        runTest {
            assertEquals(false, viewModel.countriesState.value.isLoading)
            assertEquals(emptyList<Country>(), viewModel.countriesState.value.countries)

            viewModel.initialiseCountries()

            assertEquals(true, viewModel.countriesState.value.isLoading)

            viewModel.countriesState.test {
                val state = awaitItem()
                assertThat(state.countries.isNotEmpty())
                assertThat(!state.isLoading)
                cancelAndConsumeRemainingEvents()
            }
        }
    }

    @Test
    fun `test get country and should pass`() {
        runTest {
            assertEquals(false, viewModel.countryState.value.isLoading)
            assertEquals(null, viewModel.countryState.value.selectedCountry)

            viewModel.getSelectedCountry(countryCode)

            assertEquals(true, viewModel.countryState.value.isLoading)

            viewModel.countryState.test {
                val state = awaitItem()
                assertThat(state.selectedCountry != null)
                assertThat(!state.isLoading)
                cancelAndConsumeRemainingEvents()
            }
        }
    }

    @Test
    fun `test dismiss country and should pass`() {
        runTest {
            assertEquals(false, viewModel.countryState.value.isLoading)
            assertEquals(null, viewModel.countryState.value.selectedCountry)

            viewModel.getSelectedCountry(countryCode)

            assertEquals(true, viewModel.countryState.value.isLoading)

            viewModel.countryState.test {
                val state = awaitItem()
                assertThat(state.selectedCountry != null)
                assertThat(!state.isLoading)
                cancelAndConsumeRemainingEvents()
            }

            viewModel.dismissCountry()

            viewModel.countryState.test {
                val state = awaitItem()
                assertThat(state.selectedCountry == null)
                cancelAndConsumeRemainingEvents()
            }
        }
    }
}