package com.example.dummyjson_products.presentation.products_list.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppTextField(value: String, placeholder: String, onValueChange: (String) -> Unit) {
  BasicTextField(modifier = Modifier
    .fillMaxSize()
    .padding(10.dp),
    value = value,
    onValueChange = onValueChange,
    textStyle = TextStyle(
      fontSize = 22.sp, color = MaterialTheme.colorScheme.onPrimary
    ),
    singleLine = true,
    decorationBox = { innerTextField ->
      Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        if (value.isEmpty()) {
          Text(
            text = placeholder,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            fontSize = 20.sp
          )
        }
      }
      innerTextField()
    })
}