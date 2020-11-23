package com.naveen.naveenapp.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkClient {

    private fun getClient() : OkHttpClient{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return  OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    private fun retrofit() : Retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .client(getClient())
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val  apiInterface : APIInterface = retrofit().create(APIInterface::class.java)

}