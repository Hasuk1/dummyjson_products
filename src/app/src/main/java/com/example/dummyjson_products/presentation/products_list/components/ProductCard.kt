package com.example.dummyjson_products.presentation.products_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.dummyjson_products.data.remote.Product

@Composable
fun ProductCard(productInfo: Product) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .height(140.dp)
      .padding(5.dp),
    shape = RoundedCornerShape(10.dp),
    elevation = CardDefaults.cardElevation(8.dp, 4.dp)
  ) {
    Box(modifier = Modifier.fillMaxSize()) {
      Row(modifier = Modifier.padding(10.dp)) {
        Image(
          painter = rememberImagePainter(data = productInfo.thumbnail), modifier = Modifier.clip(
            MaterialTheme.shapes.medium.copy(
              topEnd = CornerSize(10.dp),
              topStart = CornerSize(10.dp),
              bottomEnd = CornerSize(10.dp),
              bottomStart = CornerSize(10.dp)
            )
          ), contentDescription = productInfo.title
        )
        Column(modifier = Modifier.padding(15.dp)) {
          Text(
            text = productInfo.title,
            fontSize = 20.sp,
          )
          Text(
            text = productInfo.description
          )
        }
      }
    }
  }
}