package com.example.dummyjson_products.data

import com.example.dummyjson_products.data.remote.Products
import retrofit2.http.GET
import retrofit2.http.Query

interface DummyJsonApi {
  @GET("products")
  suspend fun getProductsList(@Query("skip") skip: Int, @Query("limit") limit: Int = 20): Products

  companion object {
    const val BASE_URL = "https://dummyjson.com/"
  }
}