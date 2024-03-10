package com.example.dummyjson_products.domain.repository

import com.example.dummyjson_products.data.Resource
import com.example.dummyjson_products.data.remote.Product
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
  suspend fun getProducts(skip: Int, limit: Int): Flow<Resource<List<Product>>>

//  suspend fun getProductById(id: Int): Product
}