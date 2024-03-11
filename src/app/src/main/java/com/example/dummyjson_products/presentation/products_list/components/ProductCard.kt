package com.example.dummyjson_products.presentation.products_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.dummyjson_products.data.remote.Product
import com.example.dummyjson_products.presentation.ui.icons.AppIcons.Rating
import kotlin.math.abs

@Composable
fun ProductCard(productInfo: Product, onClick: () -> Unit) {
  Column(
    modifier = Modifier
      .clickable { onClick.invoke() }
      .padding(5.dp)
      .shadow(
        elevation = 5.dp,
        shape = RoundedCornerShape(10.dp),
        ambientColor = MaterialTheme.colorScheme.inversePrimary
      )
      .height(310.dp)
      .width(200.dp)
      .background(
        color = MaterialTheme.colorScheme.primaryContainer, shape = RoundedCornerShape(10.dp)
      ),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.SpaceBetween
  ) {
    Image(
      painter = rememberAsyncImagePainter(model = productInfo.thumbnail),
      alpha = 0.9F,
      modifier = Modifier
        .padding(7.dp)
        .size(190.dp)
        .clip(
          MaterialTheme.shapes.medium.copy(
            topEnd = CornerSize(10.dp),
            topStart = CornerSize(10.dp),
            bottomEnd = CornerSize(10.dp),
            bottomStart = CornerSize(10.dp)
          )
        ),
      contentDescription = productInfo.title,
      contentScale = ContentScale.Crop
    )
    Text(
      modifier = Modifier
        .padding(top = 0.dp, start = 7.dp, end = 7.dp, bottom = 0.dp)
        .fillMaxWidth(),
      text = productInfo.title,
      fontSize = 15.sp,
      maxLines = 1,
      overflow = TextOverflow.Ellipsis,
      fontWeight = FontWeight.Bold,
      color = MaterialTheme.colorScheme.onPrimaryContainer,
      textAlign = TextAlign.Start
    )
    Text(
      modifier = Modifier
        .padding(top = 0.dp, start = 7.dp, end = 7.dp, bottom = 7.dp)
        .fillMaxWidth()
        .weight(1f),
      text = productInfo.description,
      fontSize = 12.sp,
      color = MaterialTheme.colorScheme.onSecondaryContainer,
      maxLines = 3,
      overflow = TextOverflow.Ellipsis,
      style = TextStyle.Default.copy(
        lineBreak = LineBreak.Paragraph.copy(strategy = LineBreak.Strategy.Balanced)
      )
    )
    Row(
      modifier = Modifier
        .padding(top = 0.dp, start = 7.dp, end = 7.dp, bottom = 7.dp)
        .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
    ) {
      val productPriceDiscount = productInfo.price / 100 * productInfo.discountPercentage
      Text(
        text = (productInfo.price - productPriceDiscount).toString() + "$",
        fontSize = 17.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.inverseSurface,
        textAlign = TextAlign.Start
      )
      if (abs(productPriceDiscount) > 1e-2) {
        Text(
          modifier = Modifier.padding(5.dp),
          text = productInfo.price.toString() + "$",
          fontSize = 12.sp,
          color = MaterialTheme.colorScheme.onSecondaryContainer,
          textAlign = TextAlign.Start,
          style = TextStyle(
            textDecoration = TextDecoration.LineThrough
          )
        )
      }
      Spacer(modifier = Modifier.weight(1f))
      Text(
        text = String.format("%.1f", productInfo.rating),
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        textAlign = TextAlign.Start
      )
      Icon(
        imageVector = Rating,
        contentDescription = null,
        tint = MaterialTheme.colorScheme.error
      )
    }
  }
}
