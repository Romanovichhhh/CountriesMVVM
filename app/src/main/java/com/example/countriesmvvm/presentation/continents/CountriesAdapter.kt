package com.example.countriesmvvm.presentation.continents

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countriesmvvm.databinding.ItemNestedCountryBinding

class CountriesAdapter(
    private val list: List<Pair<String, String>>,
    private val goToCountry: (String) -> Unit
) : RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder =
        CountriesViewHolder(
            ItemNestedCountryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    inner class CountriesViewHolder(private val view: ItemNestedCountryBinding) :
        RecyclerView.ViewHolder(view.root) {

        fun bind(data: Pair<String, String>) {
            with(view) {
                itemCountryCode.text = data.first
                itemCountryName.text = data.second
                itemCountryContainer.setOnClickListener {
                    goToCountry(data.first)
                }
            }
        }
    }
}

