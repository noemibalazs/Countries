package com.noemi.countries.model

data class Country(
    val code: String,
    val name: String,
    val emoji: String,
    val capital: String
)

data class DetailedCountry(
    val code: String,
    val name: String,
    val emoji: String,
    val capital: String,
    val currency: String,
    val continent: String,
    val language: List<String>
)