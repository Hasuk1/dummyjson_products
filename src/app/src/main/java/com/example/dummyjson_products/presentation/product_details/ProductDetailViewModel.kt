package com.example.dummyjson_products.presentation.product_details

import androidx.lifecycle.SavedStateHandle
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
class ProductDetailViewModel @Inject constructor(
  private val productsRepository: ProductsRepository, private val savedStateHandle: SavedStateHandle
) : ViewModel() {
  private val _product = MutableStateFlow(Product())
  val product = _product.asStateFlow()

  private val _showErrorToastChannel = Channel<Boolean>()
  val showErrorToastChannel = _showErrorToastChannel.receiveAsFlow()

  init {
    savedStateHandle.get<String>("productId")?.let { productId ->
      loadProductDetail(productId.toInt())
    }
  }

  private fun loadProductDetail(productId: Int) {
    viewModelScope.launch {
      productsRepository.getProductById(productId).collectLatest { res ->
        when (res) {
          is Resource.Loading -> {}
          is Resource.Error -> {
            _showErrorToastChannel.send(true)
          }

          is Resource.Success -> {
            _showErrorToastChannel.send(false)
            res.data?.let { product ->
              _product.value = product
            }
          }
        }
      }
    }
  }

  fun refreshProductDetail() {
    savedStateHandle.get<String>("productId")?.let { productId ->
      loadProductDetail(productId.toInt())
    }
  }
}