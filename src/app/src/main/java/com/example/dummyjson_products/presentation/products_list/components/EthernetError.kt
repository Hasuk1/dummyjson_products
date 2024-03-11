package com.example.dummyjson_products.presentation.products_list.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dummyjson_products.presentation.products_list.ProductListViewModel

@Composable
fun EthernetError(viewModel: ProductListViewModel = hiltViewModel()) {
  Text(
    text = "Couldn't reach server.\n${viewModel.errorMessage}\nCheck your internet connection.",
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
    shape = RoundedCornerShape(20.dp)
  ) {
    Text(text = "Refresh", fontSize = 25.sp)
  }
}