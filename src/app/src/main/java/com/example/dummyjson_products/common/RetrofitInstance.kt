package com.example.dummyjson_products.common

import com.example.dummyjson_products.data.DummyJsonApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
  private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
  }

  private val client: OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(interceptor)
    .build()

  val api: DummyJsonApi = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(DummyJsonApi.BASE_URL)
    .client(client)
    .build()
    .create(DummyJsonApi::class.java)
}