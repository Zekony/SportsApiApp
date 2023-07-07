package com.example.sportsapp.presentation.util

sealed interface DownloadState {
    object Success : DownloadState
    object Loading : DownloadState
    object Error : DownloadState
}