package com.example.countriesmvvm.presentation.customview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.countriesmvvm.R
import com.example.countriesmvvm.databinding.ViewCountryHeaderBinding

class CountryHeaderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr : Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val keyView : AppCompatTextView
    private val valueView : AppCompatTextView
    private val arrowView : AppCompatImageView
    private val recyclerView : RecyclerView

    init {

        View.inflate(context, R.layout.view_country_header, this)
        keyView = findViewById(R.id.country_header_key)
        valueView = findViewById(R.id.country_header_value)
        arrowView = findViewById(R.id.country_header_arrow)
        recyclerView = findViewById(R.id.country_header_recycler)

        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.CountryHeaderView,
            defStyleAttr,
            0
        )
        val keyString = typedArray.getString(R.styleable.CountryHeaderView_key)
        keyString?.let { setKeyString(it) }
        val valueString = typedArray.getString(R.styleable.CountryHeaderView_value)
        valueString?.let { setValueString(it) }
        val hasRecycler = typedArray.getBoolean(R.styleable.CountryHeaderView_hasRecycler, false)
        isViewHaveRecycler(hasRecycler)
        typedArray.recycle()
        setupViews()
    }

    fun setKeyString(string : String) {
        keyView.text = string
    }

    fun setValueString(string : String?) {
        valueView.text = string
    }

    private fun isViewHaveRecycler(hasRecycler : Boolean) {
        arrowView.isVisible = hasRecycler
    }

    private fun setupViews() {
        arrowView.setBackgroundResource((R.drawable.icon_expand_more))
        arrowView.setOnClickListener {
            recyclerView.isVisible = !recyclerView.isVisible
            if (recyclerView.isVisible) {
                arrowView.setBackgroundResource((R.drawable.icon_expand_less))
            }
            else {
                arrowView.setBackgroundResource((R.drawable.icon_expand_more))
            }
        }
    }

    fun setItems(list : List<String>) {
        if (list.isEmpty()) {
            arrowView.isVisible = false
        }
        else {
            recyclerView.apply {
                adapter = CountryHeaderAdapter(list)
            }
        }
    }
}