package com.example.dummyjson_products.presentation.product_details.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.dummyjson_products.data.remote.Product
import com.example.dummyjson_products.presentation.ui.icons.AppIcons
import kotlin.math.abs

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProductDetail(productDetail: Product, goBack: () -> Unit) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(top = 50.dp)
  ) {
    val productImageList = productDetail.images
    val pagerState = rememberPagerState(pageCount = { productImageList.size })
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .aspectRatio(1f)
    ) {
      HorizontalPager(
        state = pagerState, modifier = Modifier.fillMaxSize()
      ) { page ->
        Box(modifier = Modifier.fillMaxSize()) {
          Image(
            painter = rememberAsyncImagePainter(model = productImageList[page]),
            alpha = 0.9F,
            modifier = Modifier
              .fillMaxSize()
              .blur(80.dp),
            contentDescription = productDetail.title,
            contentScale = ContentScale.Crop
          )
          Image(
            painter = rememberAsyncImagePainter(model = productImageList[page]),
            alpha = 0.9F,
            modifier = Modifier.fillMaxSize(),
            contentDescription = productDetail.title,
            contentScale = ContentScale.Fit
          )
        }
      }
      IconButton(
        onClick = { goBack.invoke() }, colors = IconButtonDefaults.iconButtonColors(
          contentColor = MaterialTheme.colorScheme.inverseSurface
        )
      ) {
        Icon(AppIcons.ArrowBack, contentDescription = "Go Back")
      }
    }
    Row(
      Modifier
        .fillMaxWidth()
        .height(30.dp)
        .padding(vertical = 8.dp),
      horizontalArrangement = Arrangement.Center,
      verticalAlignment = Alignment.CenterVertically
    ) {
      repeat(pagerState.pageCount) { iteration ->
        val color =
          if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.onSecondaryContainer else MaterialTheme.colorScheme.onSecondary
        val sizeIndicator by animateDpAsState(
          targetValue = if (pagerState.currentPage == iteration) 8.dp else 6.dp, label = ""
        )
        Box(
          modifier = Modifier
            .padding(1.dp)
            .clip(CircleShape)
            .background(color)
            .size(sizeIndicator)
        )
      }
    }
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
      Text(
        text = productDetail.brand,
        color = MaterialTheme.colorScheme.onPrimary,
        modifier = Modifier
          .clip(RoundedCornerShape(10.dp))
          .background(MaterialTheme.colorScheme.primaryContainer)
          .padding(5.dp)
      )
      Text(
        text = productDetail.title,
        color = MaterialTheme.colorScheme.onPrimary,
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp,
        modifier = Modifier.padding(vertical = 10.dp)
      )
      Row(modifier = Modifier.fillMaxWidth()) {
        Text(
          text = String.format("%.1f", productDetail.rating),
          fontSize = 15.sp,
          fontWeight = FontWeight.Bold,
          color = MaterialTheme.colorScheme.onPrimaryContainer,
          textAlign = TextAlign.Start
        )
        Icon(
          imageVector = AppIcons.Rating,
          contentDescription = null,
          tint = MaterialTheme.colorScheme.error
        )
      }
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .padding(vertical = 5.dp)
      ) {
        val productPriceDiscount = productDetail.price / 100 * productDetail.discountPercentage
        Text(
          text = (productDetail.price - productPriceDiscount).toString() + "$",
          fontSize = 30.sp,
          fontWeight = FontWeight.Bold,
          color = MaterialTheme.colorScheme.inverseSurface,
          textAlign = TextAlign.Start
        )
        if (abs(productPriceDiscount) > 1e-2) {
          Text(
            modifier = Modifier.padding(5.dp),
            text = productDetail.price.toString() + "$",
            fontSize = 15.sp,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            textAlign = TextAlign.Start,
            style = TextStyle(
              textDecoration = TextDecoration.LineThrough
            )
          )
        }
      }
      Text(
        modifier = Modifier
          .padding(top = 5.dp, bottom = 10.dp)
          .fillMaxWidth(),
        text = productDetail.description,
        fontSize = 17.sp,
        color = MaterialTheme.colorScheme.onSecondaryContainer,
        style = TextStyle.Default.copy(
          lineBreak = LineBreak.Paragraph.copy(strategy = LineBreak.Strategy.Balanced)
        )
      )
      Text(
        text = productDetail.category,
        fontSize = 15.sp,
        color = MaterialTheme.colorScheme.onPrimary,
        modifier = Modifier
          .clip(RoundedCornerShape(10.dp))
          .background(MaterialTheme.colorScheme.primaryContainer)
          .padding(5.dp)
      )
    }
  }
}