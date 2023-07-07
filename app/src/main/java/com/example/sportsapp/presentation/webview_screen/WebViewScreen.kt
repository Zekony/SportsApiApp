package com.example.sportsapp.presentation.webview_screen

import android.graphics.Bitmap
import android.util.Log
import android.webkit.WebView
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.web.*

@Composable
fun WebViewScreen(
    urlWord: String,
    viewModel: WebViewScreenViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getUrlWord(urlWord)
    }
    val state = viewModel.state.collectAsState().value
    val webViewState = rememberWebViewState(url = state.url)
    val navigator = rememberWebViewNavigator()
    var textFieldValue by remember(webViewState.lastLoadedUrl) {
        mutableStateOf(webViewState.lastLoadedUrl ?: state.url)
    }

    Column {
        Row(modifier = Modifier.padding(all = 12.dp)) {
            IconButton(
                onClick = { navigator.navigateBack() }, modifier = Modifier.size(20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            BasicTextField(
                modifier = Modifier.weight(9f),
                value = textFieldValue,
                onValueChange = {
                    textFieldValue = it
                    viewModel.getNewUrl(it)
                },
                maxLines = 1
            )
            if (webViewState.errorsForCurrentRequest.isNotEmpty()) {
                Icon(
                    modifier = Modifier
                        .weight(1f),
                    imageVector = Icons.Default.Warning,
                    contentDescription = "Error",
                    tint = Color.Red
                )
            }
        }

        val loadingState = webViewState.loadingState
        if (loadingState is LoadingState.Loading) {
            LinearProgressIndicator(
                progress = loadingState.progress,
                modifier = Modifier.fillMaxWidth()
            )
        }

        val webClient = remember {
            object : AccompanistWebViewClient() {
                override fun onPageStarted(
                    view: WebView?,
                    url: String?,
                    favicon: Bitmap?
                ) {
                    super.onPageStarted(view, url, favicon)
                    Log.d("Accompanist WebView", "Page started loading for $url")
                }
            }
        }
        WebView(
            state = webViewState,
            modifier = Modifier.weight(1f),
            navigator = navigator,
            onCreated = { webView ->
                webView.settings.javaScriptEnabled = true
            },
            client = webClient
        )
    }
}