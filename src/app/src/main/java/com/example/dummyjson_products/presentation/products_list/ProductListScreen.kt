package com.example.dummyjson_products.presentation.products_list

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dummyjson_products.presentation.products_list.components.ProductCard
import kotlinx.coroutines.flow.collectLatest

@Composable
fun ProductsListScreen(viewModel: ProductListViewModel = hiltViewModel()) {
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
        Text(
          text = "Couldn't reach server.\nCheck your internet connection.",
          color = MaterialTheme.colorScheme.onPrimary,
          fontSize = 20.sp,
          textAlign = TextAlign.Center
        )
        Button(
          onClick = { viewModel.refreshProducts() },
          modifier = Modifier
            .width(200.dp)
            .height(60.dp)
            .padding(5.dp),
          colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSecondary
          ),
          elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 10.dp, pressedElevation = 0.dp
          ),
          shape = RoundedCornerShape(20.dp),
        ) {
          Text(text = "Refresh", fontSize = 25.sp)
        }
      } else {
        CircularProgressIndicator(color = MaterialTheme.colorScheme.scrim)
      }
    }
  } else {
    val lazyListState = rememberLazyListState()
    isError = false
    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
        .padding(top = 40.dp),
      state = lazyListState,
      horizontalAlignment = Alignment.CenterHorizontally,
      contentPadding = PaddingValues(16.dp)
    ) {
      items(productList.size) { index ->
        ProductCard(productList[index])
        if (index == productList.size - 1) viewModel.loadMoreProducts()
      }
    }
  }
}


