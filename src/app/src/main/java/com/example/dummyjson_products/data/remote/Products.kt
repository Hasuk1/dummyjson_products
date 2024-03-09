package com.example.dummyjson_products.data.remote

data class Products(
  val products: List<Product>,
  val limit: Int,
  val skip: Int,
  val total: Int,
)
