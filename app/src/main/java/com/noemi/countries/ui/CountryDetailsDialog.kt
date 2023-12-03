package com.noemi.countries.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.noemi.countries.R
import com.noemi.countries.databinding.DialogCountryDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryDetailsDialog : DialogFragment() {

    private val countryViewModel: CountryViewModel by viewModels()
    private lateinit var viewBinding: DialogCountryDetailsBinding

    private var code: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.CountriesDialogStyle)
        isCancelable = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        code = arguments?.getString(COUNTRY_CODE_KEY, "") ?: ""
        countryViewModel.getSelectedCountry(code)

        viewBinding = DialogCountryDetailsBinding.bind(inflater.inflate(R.layout.dialog_country_details, null))

        with(viewBinding) {
            lifecycleOwner = this@CountryDetailsDialog
            viewModel = countryViewModel
        }
        return viewBinding.root
    }

    override fun dismiss() {
        super.dismiss()
        countryViewModel.dismissCountry()
    }

    companion object {
        const val TAG = "CountryDetailsDialog"
        const val COUNTRY_CODE_KEY = "country_code_key"

        fun getInstance(code: String): CountryDetailsDialog {
            return CountryDetailsDialog()
                .apply {
                    arguments = Bundle().apply {
                        putString(COUNTRY_CODE_KEY, code)
                    }
                }
        }
    }
}