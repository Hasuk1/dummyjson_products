package com.example.dummyjson_products.domain.repository

import com.example.dummyjson_products.data.DummyJsonApi
import com.example.dummyjson_products.data.Resource
import com.example.dummyjson_products.data.remote.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
  private val api: DummyJsonApi
) : ProductsRepository {
  override suspend fun getProducts(skip: Int, limit: Int): Flow<Resource<List<Product>>> {
    return flow {
      val productsFromApi = try {
        api.getProductsList(skip, limit)
      } catch (e: HttpException) {
        e.printStackTrace()
        emit(Resource.Error(e.localizedMessage ?: "Unexpected error"))
        return@flow
      } catch (e: IOException) {
        e.printStackTrace()
        emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        return@flow
      } catch (e: Exception) {
        e.printStackTrace()
        emit(Resource.Error(e.localizedMessage ?: "Unexpected error"))
        return@flow
      }
      emit(Resource.Success(productsFromApi.products))
    }
  }

  override suspend fun getProductById(id: Int): Flow<Resource<Product>> {
    return flow {
      val productFromApi = try {
        api.getProductDetail(id)
      } catch (e: HttpException) {
        e.printStackTrace()
        emit(Resource.Error(e.localizedMessage ?: "Unexpected error"))
        return@flow
      } catch (e: IOException) {
        e.printStackTrace()
        emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        return@flow
      } catch (e: Exception) {
        e.printStackTrace()
        emit(Resource.Error(e.localizedMessage ?: "Unexpected error"))
        return@flow
      }
      emit(Resource.Success(productFromApi))
    }
  }
}