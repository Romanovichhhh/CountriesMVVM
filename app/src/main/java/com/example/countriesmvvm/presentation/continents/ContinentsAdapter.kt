package com.example.countriesmvvm.presentation.continents

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.countriesmvvm.R
import com.example.countriesmvvm.databinding.ItemContinentBinding
import com.example.countriesmvvm.domain.models.Continent

class ContinentsAdapter(
    private val list: List<Continent>,
    private val goToCountry: (String) -> Unit
) : RecyclerView.Adapter<ContinentsAdapter.ContinentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContinentViewHolder =
        ContinentViewHolder(
            ItemContinentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: ContinentViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    inner class ContinentViewHolder(private val view: ItemContinentBinding) :
        RecyclerView.ViewHolder(view.root) {

        private val context = view.root.context

        fun bind(data: Continent) {
            with(view) {
                continentCode.text = data.code
                continentTitle.text = data.name
                continentExpandButton.setBackgroundResource((R.drawable.icon_expand_more))
                continentExpandButton.setOnClickListener {
                    continentCountriesRecycler.isVisible = !continentCountriesRecycler.isVisible
                    if (continentCountriesRecycler.isVisible) {
                        continentExpandButton.setBackgroundResource((R.drawable.icon_expand_less))
                    }
                    else {
                        continentExpandButton.setBackgroundResource(R.drawable.icon_expand_more)
                    }
                }
                continentCountriesRecycler.adapter = CountriesAdapter(data.countries, goToCountry)
            }
        }

    }
}

