package com.wongislandd.infinityindex.infra.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewState
import com.wongislandd.infinityindex.entities.comics.models.RelatedLink

@Composable
private fun LinkOut(link: RelatedLink, modifier: Modifier = Modifier) {
    var isWebViewOpened by remember { mutableStateOf(false) }
    val webViewState = rememberWebViewState(link.url)
    val actionWording = if (isWebViewOpened) "Close" else "Open"
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
            onClick = { isWebViewOpened = !isWebViewOpened },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.secondary,
                contentColor = MaterialTheme.colors.onSecondary
            )
        ) {
            Text("$actionWording ${link.type.displayName} Page")
        }
        if (isWebViewOpened) {
            if (webViewState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.padding(8.dp),
                    color = MaterialTheme.colors.secondary
                )
            }
            WebView(webViewState)
            Button(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                onClick = { isWebViewOpened = false },
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.secondary,
                    contentColor = MaterialTheme.colors.onSecondary
                )
            ) {
                Text("$actionWording ${link.type.displayName} Page")
            }
        }
    }
}

@Composable
fun MarvelLinks(links: List<RelatedLink>, modifier: Modifier = Modifier) {
    DetailsSection("Marvel Links", modifier = modifier, additionalTitlePadding = 16.dp) {
        links.forEach { link ->
            LinkOut(link)
        }
    }
}