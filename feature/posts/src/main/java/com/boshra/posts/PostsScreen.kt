package com.boshra.posts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.boshra.league.domain.model.GeneralError
import com.boshra.league.feature.posts.R
import com.boshra.posts.model.PostUiModel

@Composable
fun PostsScreen(
  viewModel: PostsViewModel,
) {
  val state by viewModel.state.collectAsStateWithLifecycle()

  LaunchedEffect(Unit) {
    viewModel.getPosts()
  }
  if (state.isLoading) {
    CircularProgressIndicator(
      modifier = Modifier
        .wrapContentSize(),
    )
  } else if (state.error != null) {
    ShowError(
      error = state.error!!,
      message = state.message ?: "",
      onRefresh = state.tryAgainFunc ?: {},
    )
  } else {
    LazyColumn(
      contentPadding = PaddingValues(top = 16.dp),
      verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
      items(state.postsSections) { post ->
        PostSectionRow(
          item = post,
        )
      }
    }
  }
}

@Composable
fun PostSectionRow(
  item: PostUiModel,
) {
  Column {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 12.dp, horizontal = 16.dp),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      AsyncImage(
        model = item.avatarUrl,
        contentDescription = null,
        modifier = Modifier
          .size(48.dp)
          .clip(CircleShape),
        contentScale = ContentScale.Crop,
      )
      Text(
        text = item.username ?: "",
        style = MaterialTheme.typography.titleMedium,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.padding(start = 16.dp),
      )
    }
    Text(
      text = item.title ?: "",
      style = MaterialTheme.typography.titleSmall,
      maxLines = 1,
      overflow = TextOverflow.Ellipsis,
      modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
    )
    Text(
      text = item.description ?: "",
      style = MaterialTheme.typography.bodySmall,
      maxLines = 5,
      overflow = TextOverflow.Ellipsis,
      modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
    )
    Spacer(modifier = Modifier.width(16.dp))
    HorizontalDivider(
      color = Color(0xFFE7E7E7),
      thickness = 1.dp,
      modifier = Modifier.padding(start = 16.dp, end = 16.dp),
    )
  }
}

@Composable
fun ShowError(error: GeneralError, message: String, onRefresh: () -> Unit) {
  val composition by rememberLottieComposition(
    LottieCompositionSpec.RawRes(
      if (error is GeneralError.NetworkError) {
        R.raw.network_lost
      } else {
        R.raw.not_found
      },
    ),
  )
  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(horizontal = 16.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally,

  ) {
    LottieAnimation(
      modifier = Modifier.scale(0.8f),
      composition = composition,
    )
    Spacer(modifier = Modifier.size(8.dp))
    Text(
      text = message,
      textAlign = TextAlign.Center,
      style = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
      ),
    )

    Spacer(modifier = Modifier.size(60.dp))
    Button(
      onClick = onRefresh,
    ) {
      Text(text = "Refresh")
    }
  }
}
