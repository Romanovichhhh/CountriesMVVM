package com.example.countriesmvvm.data

import com.apollographql.apollo3.ApolloCall
import com.apollographql.apollo3.ApolloClient
import com.example.countriesmvvm.ContinentQuery
import com.example.countriesmvvm.ContinentsQuery
import com.example.countriesmvvm.CountryQuery
import com.example.countriesmvvm.data.common.BASE_URL

object GraphQLClient {

    private fun apolloClient(): ApolloClient =
        ApolloClient.Builder().serverUrl(BASE_URL).build()

    fun getContinents(): ApolloCall<ContinentsQuery.Data> =
        apolloClient().query(ContinentsQuery())

    fun getContinent(code: String) =
        apolloClient().query(ContinentQuery(code))

    fun getCountry(continentCode: String) =
        apolloClient().query(CountryQuery(continentCode))

}