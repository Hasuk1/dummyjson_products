package com.example.dummyjson_products.presentation.products_list

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.dummyjson_products.common.MessageBox
import com.example.dummyjson_products.presentation.products_list.components.ProductList
import com.example.dummyjson_products.presentation.products_list.components.SearchBar
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
  val inputValue = remember { mutableStateOf("") }

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

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(top = 50.dp, start = 10.dp, end = 10.dp)
  ) {
    if (isError) {
      MessageBox(message = "Couldn't reach server.\nCheck your internet connection.") {
        viewModel.refreshProductsList()
      }
    } else {
      if (productList.isEmpty() && inputValue.value == "") {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
          CircularProgressIndicator(color = MaterialTheme.colorScheme.scrim)
        }
      } else {
        isError = false
        SearchBar(inputValue,
          searchUnit = { viewModel.loadFoundProducts(inputValue.value) },
          removeUnit = {
            viewModel.refreshProductsList(true)
            inputValue.value = ""
          })
        if (productList.isEmpty() && inputValue.value != "") {
          MessageBox(message = "Nothing was found.\nTry to write the name of the product\nin a different way.") {
            viewModel.refreshProductsList(true)
            inputValue.value = ""
          }
        } else {
          Spacer(modifier = Modifier.size(10.dp))
          ProductList(productList, navController)
        }
      }
    }
  }
}
