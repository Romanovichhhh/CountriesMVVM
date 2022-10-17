package com.example.countriesmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.apollographql.apollo3.ApolloClient
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        lifecycleScope.launch {
//            getContinent()
//        }


    }

//    suspend fun getContinent() {
//        val apolloClient = ApolloClient.Builder()
//            .serverUrl("https://countries.trevorblades.com")
//            .build()
//        val result = apolloClient.query(GetContinentQuery("SA")).execute()
//        Log.d("FFF", result.toString())
//    }
}