package com.example.countriesmvvm.presentation.country

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.countriesmvvm.databinding.ActivityCountryBinding
import com.example.countriesmvvm.domain.models.Country
import com.example.countriesmvvm.utils.COUNTRY_CODE
import com.example.countriesmvvm.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryActivity : AppCompatActivity() {

    private val viewModel: CountryViewModel by viewModels()
    private lateinit var binding: ActivityCountryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observe()
        getCountryCode()?.let { viewModel.getCountryByCode(it) }
    }

    private fun observe() {
        viewModel.countryLive.observe(this) {
            setupViews(it)
        }
        viewModel.errorResultLive.observe(this) {
            when (it.messageResource) {
                is Int -> toast(getString(it.messageResource))
                is Error? -> {
                    it.messageResource?.let { errorMessage ->
                        toast(errorMessage.message ?: "Unexpected error")
                    }
                }
            }
        }
        viewModel.progressLive.observe(this) {
            binding.activityCountriesProgress.isVisible = it
        }
    }

    private fun setupViews(country: Country) {
        with(binding) {
            countryEmoji.text = country.emoji
            countryName.setValueString(country.name)
            countryNative.setValueString(country.native)
            countryPhone.setValueString(country.phone)
            countryCapital.setValueString(country.capital)
            countryContinent.setValueString(country.continentName)
            countryLanguages.setItems(country.languages)
            countryStates.setItems(country.states)

        }
    }

    private fun getCountryCode() = intent.extras?.getString(COUNTRY_CODE)
}