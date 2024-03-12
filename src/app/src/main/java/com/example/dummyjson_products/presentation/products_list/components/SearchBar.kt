package com.example.dummyjson_products.presentation.products_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dummyjson_products.presentation.ui.icons.AppIcons

@Composable
fun SearchBar(inputValue: MutableState<String>, searchUnit: () -> Unit, removeUnit: () -> Unit) {
  Row(
    modifier = Modifier
      .fillMaxWidth()
      .height(50.dp)
      .background(
        color = MaterialTheme.colorScheme.primaryContainer, shape = RoundedCornerShape(15.dp)
      ), verticalAlignment = Alignment.CenterVertically
  ) {
    IconButton(
      onClick = { searchUnit.invoke() }, colors = IconButtonDefaults.iconButtonColors(
        contentColor = MaterialTheme.colorScheme.onPrimary
      )
    ) {
      Icon(AppIcons.Search, contentDescription = "Go Back")
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopEnd) {
      AppTextField(inputValue.value, "Find products") { inputValue.value = it }
      Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Absolute.Right
      ) {
        IconButton(
          onClick = {
            if (inputValue.value.isNotBlank()) {
              removeUnit.invoke()
            }
          }, colors = IconButtonDefaults.iconButtonColors(
            contentColor = MaterialTheme.colorScheme.onPrimary
          )
        ) {
          Icon(AppIcons.Close, contentDescription = "Delete text")
        }
      }
    }
  }
}
