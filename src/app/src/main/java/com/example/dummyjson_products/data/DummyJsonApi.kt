package com.example.dummyjson_products.data

import com.example.dummyjson_products.data.remote.Product
import com.example.dummyjson_products.data.remote.Products
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DummyJsonApi {
  @GET("products/search")
  suspend fun getProductsList(
    @Query("skip") skip: Int, @Query("limit") limit: Int, @Query("q") query: String
  ): Products

  @GET("products/{productId}")
  suspend fun getProductDetail(@Path("productId") productId: Int): Product

  companion object {
    const val BASE_URL = "https://dummyjson.com/"
  }
}