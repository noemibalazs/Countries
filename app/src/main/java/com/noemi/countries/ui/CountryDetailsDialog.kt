package com.noemi.countries.ui

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.noemi.countries.R
import com.noemi.countries.model.DetailedCountry

class CountryDetailsDialog : DialogFragment() {

    private var country: DetailedCountry? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.CountriesDialogStyle)
        isCancelable = true
    }


    companion object {
        const val TAG = "CountryDetailsDialog"

        fun getInstance(country: DetailedCountry?): CountryDetailsDialog {
            return CountryDetailsDialog()
        }
    }
}