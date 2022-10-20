package com.example.countriesmvvm.presentation.customview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.countriesmvvm.databinding.ViewCountryHeaderBinding

class CountryHeaderAdapter(
    private val list: List<String>
) : RecyclerView.Adapter<CountryHeaderAdapter.CountryHeaderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHeaderAdapter.CountryHeaderViewHolder =
        CountryHeaderViewHolder(
            ViewCountryHeaderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(
        holder: CountryHeaderAdapter.CountryHeaderViewHolder,
        position: Int
    ) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    inner class CountryHeaderViewHolder(private val view: ViewCountryHeaderBinding) :
        RecyclerView.ViewHolder(view.root) {

        fun bind(data: String) {
            with(view) {
                countryHeaderArrow.isVisible = false
                countryHeaderArrow.isVisible = false
                countryHeaderValue.text = data
            }
        }
    }
}
