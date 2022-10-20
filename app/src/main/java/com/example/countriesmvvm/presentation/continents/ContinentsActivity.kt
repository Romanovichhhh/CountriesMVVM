package com.example.countriesmvvm.presentation.continents

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.countriesmvvm.databinding.ActivtyContinentsBinding
import com.example.countriesmvvm.domain.models.Continent
import com.example.countriesmvvm.presentation.country.CountryActivity
import com.example.countriesmvvm.utils.COUNTRY_CODE
import com.example.countriesmvvm.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContinentsActivity : AppCompatActivity() {

    private val viewModel: ContinentsViewModel by viewModels()
    private lateinit var binding: ActivtyContinentsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivtyContinentsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observe()
        viewModel.getListOfContinents()
    }

    private fun observe() {
        viewModel.continentsListLive.observe(this) {
            setContinentsList(it)
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
            binding.activityContinentsProgress.isVisible = it
        }
    }

    private fun setContinentsList(list: List<Continent>) {
        with(binding.activityContinentsRecycler) {
            adapter = ContinentsAdapter(list) { goToCountry(it) }
        }
    }

    private fun goToCountry(code: String) {
        Intent(this, CountryActivity::class.java).apply {
            putExtra(COUNTRY_CODE, code)
            startActivity(this)
        }
    }

}