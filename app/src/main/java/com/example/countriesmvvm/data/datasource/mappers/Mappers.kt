package com.example.countriesmvvm.data.datasource.mappers

import com.example.countriesmvvm.ContinentQuery
import com.example.countriesmvvm.ContinentsQuery
import com.example.countriesmvvm.CountryQuery
import com.example.countriesmvvm.domain.models.Continent
import com.example.countriesmvvm.domain.models.Country

fun ContinentsQuery.Data.mapToDomainModel(): List<Continent> {
    return this.continents.map { continent ->
        Continent(
            code = continent.code,
            name = continent.name,
            countries = continent.countries.map { Pair(it.code, it.name) }
        )
    }
}

fun CountryQuery.Data.mapToDomainModel(): Country {
    return Country(
        code = country?.code ?: "",
        name = country?.name ?: "",
        native = country?.native ?: "",
        phone = "+${country?.phone}" ?: "",
        continentName = country?.continent?.name ?: "",
        capital = country?.capital ?: "",
        currency = country?.currency ?: "",
        languages = country?.languages?.map {
            "${it.name ?: ""}(${it.native ?: ""})"
        } ?: emptyList(),
        states = country?.states?.map { it.name } ?: emptyList(),
        emoji = country?.emoji
    )
}