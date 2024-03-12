package com.example.dummyjson_products.presentation.products_list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.dummyjson_products.common.AppScreens
import com.example.dummyjson_products.data.remote.Product
import com.example.dummyjson_products.presentation.products_list.ProductListViewModel

@Composable
fun ProductList(
  productList: List<Product>,
  navController: NavHostController,
  viewModel: ProductListViewModel = hiltViewModel()
) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .clip(RoundedCornerShape(15.dp))
  ) {
    val lazyVerticalGridState = rememberLazyGridState()
    LazyVerticalGrid(
      columns = GridCells.Adaptive(150.dp),
      verticalArrangement = Arrangement.spacedBy(10.dp),
      horizontalArrangement = Arrangement.spacedBy(10.dp),
      modifier = Modifier.fillMaxSize(),
      state = lazyVerticalGridState,
    ) {
      items(productList.size) { index ->
        ProductCard(productList[index]) {
          navController.navigate(AppScreens.ProductDetailScreen.route + "/${productList[index].id}")
        }
        if (index == productList.size - 1) viewModel.loadMoreProducts()
      }
    }
  }
}