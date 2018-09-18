package com.anggitprayogo.footballapp.fotballapp.network.retrofit

import com.anggitprayogo.footballapp.fotballapp.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    private fun initRetrofit(): Retrofit{
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun<T> createService(service: Class<T>): T{
        return initRetrofit().create(service)
    }

}