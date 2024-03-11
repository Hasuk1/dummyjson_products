package com.example.dummyjson_products.presentation.products_list

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
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
import androidx.navigation.NavHostController
import com.example.dummyjson_products.presentation.products_list.components.EthernetError
import com.example.dummyjson_products.presentation.products_list.components.ProductList
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProductsListScreen(
  navController: NavHostController, viewModel: ProductListViewModel = hiltViewModel()
) {
  val productList by viewModel.products.collectAsState()
  val context = LocalContext.current
  var isError by remember {
    mutableStateOf(false)
  }

  LaunchedEffect(key1 = viewModel.showErrorToastChannel) {
    viewModel.showErrorToastChannel.collectLatest { show ->
      if (show) {
        Toast.makeText(
          context, "Error", Toast.LENGTH_SHORT
        ).show()
        isError = true
      }
    }
  }

  if (productList.isEmpty()) {
    Column(
      modifier = Modifier.fillMaxSize(),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      if (isError) {
        EthernetError()
      } else {
        CircularProgressIndicator(color = MaterialTheme.colorScheme.scrim)
      }
    }
  } else {
    isError = false
    ProductList(productList, navController)
  }
}
