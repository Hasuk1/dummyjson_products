package com.example.dummyjson_products.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MessageBox(message: String, refreshFun: () -> Unit) {
  Column(
    modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    Text(
      text = message,
      color = MaterialTheme.colorScheme.onPrimary,
      fontSize = 20.sp,
      textAlign = TextAlign.Center
    )
    Button(
      onClick = { refreshFun.invoke() },
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
}