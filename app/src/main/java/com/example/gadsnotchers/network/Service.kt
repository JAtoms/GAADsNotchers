package com.example.gadsnotchers.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://gadsapi.herokuapp.com"
private const val BASE_URL2 = "https://docs.google.com/forms/d/e/"

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


object Network {
    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val gadsServiice = retrofit.create(Endpoints::class.java)
}

private val okHttpClient = OkHttpClient.Builder()
    .addInterceptor { chain ->
        val original = chain.request()

        val requestBuilder = original.newBuilder()
            .method(original.method(), original.body())

        val request = requestBuilder.build()
        chain.proceed(request)
    }.build()

object Network2 {
    // Configure retrofit to parse JSON and use coroutines
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL2)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(okHttpClient)
        .build()

    val sendResponseService = retrofit.create(Endpoints::class.java)
}
