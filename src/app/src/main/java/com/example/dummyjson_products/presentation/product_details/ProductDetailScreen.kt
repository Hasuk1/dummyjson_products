package com.example.dummyjson_products.presentation.product_details

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dummyjson_products.common.MessageBox
import com.example.dummyjson_products.presentation.product_details.components.ProductDetail
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProductDetailScreen(goBack: () -> Unit, viewModel: ProductDetailViewModel = hiltViewModel()) {
  val productDetail by viewModel.product.collectAsState()


  val context = LocalContext.current
  var isError by remember {
    mutableStateOf(false)
  }

  LaunchedEffect(key1 = viewModel.showErrorToastChannel) {
    viewModel.showErrorToastChannel.collectLatest { show ->
      isError = show
      if (show) {
        Toast.makeText(
          context, "Error", Toast.LENGTH_SHORT
        ).show()
      }
    }
  }

  if (productDetail.id == 0) {
    Column(
      modifier = Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      if (isError) {
        MessageBox(message = "Couldn't reach server.\nCheck your internet connection.") {
          viewModel.refreshProductDetail()
        }
      } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
          CircularProgressIndicator(color = MaterialTheme.colorScheme.scrim)
        }
      }
    }
  } else {
    ProductDetail(productDetail) {
      goBack.invoke()
    }
  }
}

