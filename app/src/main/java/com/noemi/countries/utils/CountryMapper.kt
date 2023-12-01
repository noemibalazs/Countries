package com.noemi.countries.utils

import com.noemi.countries.CountriesQuery
import com.noemi.countries.CountryQuery
import com.noemi.countries.model.Country
import com.noemi.countries.model.DetailedCountry

fun CountryQuery.Country.toDetailedCountry(): DetailedCountry = DetailedCountry(
    code = code,
    name = name,
    emoji = emoji,
    capital = capital ?: "No capital at this time",
    currency = currency ?: "No currency at this time",
    continent = continent.name,
    language = languages.map { it.name }
)

fun CountriesQuery.Country.toCountry(): Country = Country(
    code = code,
    name = name,
    emoji = emoji,
    capital = capital ?: "No capital at this time"
)