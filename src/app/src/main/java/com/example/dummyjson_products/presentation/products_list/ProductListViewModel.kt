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

  private val _total = MutableStateFlow(0)
  val total = _total.asStateFlow()

  private val _showErrorToastChannel = Channel<Boolean>()
  val showErrorToastChannel = _showErrorToastChannel.receiveAsFlow()

  private val _pageSize = 20
  private var _currentSkip = 0
  private var _query = ""

  init {
    loadProducts()
  }

  private fun loadProducts() {
    viewModelScope.launch {
      productsRepository.getProducts(_currentSkip, _pageSize, _query).collectLatest { res ->
        when (res) {
          is Resource.Success -> {
            _showErrorToastChannel.send(false)
            res.data?.products?.let { products ->
              _products.value += products
              _total.value = res.data.total
              _currentSkip += _pageSize
            }
          }

          is Resource.Error -> {
            _showErrorToastChannel.send(true)
          }

          is Resource.Loading -> {}
        }
      }
    }
  }

  fun refreshProductsList(cleanQuery: Boolean = false) {
    viewModelScope.launch {
      _currentSkip = 0
      if (cleanQuery) _query = ""
      _products.value = emptyList()
      loadProducts()
    }
  }

  fun loadMoreProducts() {
    loadProducts()
  }

  fun loadFoundProducts(query: String) {
    viewModelScope.launch {
      _query = query
      _currentSkip = 0
      _products.value = emptyList()
      loadProducts()
    }
  }
}