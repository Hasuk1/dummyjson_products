package com.example.dummyjson_products.presentation.products_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dummyjson_products.data.Resource
import com.example.dummyjson_products.data.remote.Product
import com.example.dummyjson_products.domain.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
  private val productsRepository: ProductsRepository
) : ViewModel() {

  private val _products = MutableStateFlow<List<Product>>(emptyList())
  val products = _products.asStateFlow()

  private val _showErrorToastChannel = Channel<Boolean>()
  val showErrorToastChannel = _showErrorToastChannel.receiveAsFlow()

  var errorMessage = ""
    set(value) {
      if (field == value) field = value
    }

  private val pageSize = 20
  private var currentSkip = 0

  init {
    loadProducts()
  }

  private fun loadProducts() {
    viewModelScope.launch {
      productsRepository.getProducts(currentSkip, pageSize).collectLatest { res ->
        when (res) {
          is Resource.Success -> {
            res.data?.let { products ->
              _products.value += products
              currentSkip += pageSize // Move to the next batch
            }
          }

          is Resource.Error -> {
            _showErrorToastChannel.send(true)
            errorMessage = res.message.toString()
          }

          is Resource.Loading -> {
            // todo
          }
        }
      }
    }
  }

  fun refreshProducts() {
    viewModelScope.launch {
      currentSkip = 0
      _products.value = emptyList()
      loadProducts()
    }
  }

  fun loadMoreProducts() {
    loadProducts()
  }
}