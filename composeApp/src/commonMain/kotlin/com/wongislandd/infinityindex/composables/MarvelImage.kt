package com.wongislandd.infinityindex.composables

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin

@Composable
fun MarvelImage(imageUrl: String, modifier: Modifier) {
    CoilImage(
        modifier = modifier,
        imageModel = { imageUrl },
        imageOptions = ImageOptions(
            contentScale = ContentScale.Inside,
            alignment = Alignment.Center
        ),
        component = rememberImageComponent {
            +ShimmerPlugin()
        },
        failure = {
            Text("Could not load image.")
        }
    )
}