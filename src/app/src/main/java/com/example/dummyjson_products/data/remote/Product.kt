package com.example.dummyjson_products.data.remote

data class Product(
  val id: Int = 0,
  val title: String = "",
  val description: String = "",
  val price: Int = 0,
  val discountPercentage: Double = 0.0,
  val rating: Double = 0.0,
  val stock: Int = 0,
  val brand: String = "",
  val category: String = "",
  val thumbnail: String = "",
  val images: List<String> = emptyList()
)
