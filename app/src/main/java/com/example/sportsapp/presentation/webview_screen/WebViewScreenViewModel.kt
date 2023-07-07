package com.example.sportsapp.presentation.webview_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class WebViewScreenViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(WebViewScreenState())
    val state = _state.asStateFlow()

    fun getUrlWord(word: String) {
        _state.update {
            it.copy(
                url = state.value.url + word
            )
        }
    }

    fun getNewUrl(inputText: String) {
        _state.update { currentState ->
            currentState.copy(
                url = inputText
            )
        }
        Log.d("WebViewScreenViewModel", "getNewUrl: ${state.value.url}")
    }
}