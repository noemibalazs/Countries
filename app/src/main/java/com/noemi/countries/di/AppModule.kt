package com.noemi.countries.di

import com.apollographql.apollo3.ApolloClient
import com.noemi.countries.datasource.ApolloCountryClient
import com.noemi.countries.datasource.CountryClient
import com.noemi.countries.domain.CountryRepository
import com.noemi.countries.domain.CountryRepositoryImpl
import com.noemi.countries.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesApolloClient(): ApolloClient = ApolloClient.Builder().serverUrl(BASE_URL).build()


    @Provides
    @Singleton
    fun providesApolloCountryClient(apolloClient: ApolloClient): CountryClient =
        ApolloCountryClient(apolloClient)

    @Provides
    @Singleton
    fun providesCountryRepository(countryClient: CountryClient): CountryRepository =
        CountryRepositoryImpl(countryClient)
}