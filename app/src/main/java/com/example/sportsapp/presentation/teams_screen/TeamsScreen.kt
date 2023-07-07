package com.example.sportsapp.presentation.teams_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.sportsapp.R
import com.example.sportsapp.presentation.util.DownloadState
import com.example.sportsapp.presentation.util.LoadingAnimation3

@Composable
fun TeamsScreen(
    id: String,
    modifier: Modifier = Modifier,
    viewModel: TeamsScreenViewModel = hiltViewModel(),
    toPlayersScreen: (String) -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getTeamsIdByCountryId(id)
    }
    val state = viewModel.state.collectAsStateWithLifecycle().value
    val list = state.teamsList
    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            modifier = modifier,
            bottomBar = {}
        ) { padding ->
            when (state.downloadState) {
                DownloadState.Success -> {
                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(padding)
                            .fillMaxSize()
                    ) {
                        Text(
                            text = "Choose Team",
                            fontSize = 26.sp,
                            modifier = Modifier
                                .padding(20.dp)
                        )
                        LazyVerticalGrid(
                            columns = GridCells.Adaptive(150.dp),
                            verticalArrangement = Arrangement.spacedBy(25.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            items(list.size) { item ->
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(200.dp)
                                        .clickable { toPlayersScreen(list[item].id.toString()) }
                                ) {
                                    AsyncImage(
                                        model = ImageRequest.Builder(LocalContext.current)
                                            .data(list[item].img)
                                            .memoryCachePolicy(CachePolicy.ENABLED)
                                            .diskCachePolicy(CachePolicy.ENABLED)
                                            .crossfade(true)
                                            .build(),
                                        contentDescription = list[item].name,
                                        error = painterResource(R.drawable.error_image),
                                        placeholder = painterResource(R.drawable.loading_img),
                                        contentScale = ContentScale.FillBounds,
                                        modifier = Modifier
                                            .size(160.dp)
                                    )
                                    Text(
                                        text = list[item].name,
                                        fontSize = 20.sp,
                                        modifier = Modifier
                                            .padding(8.dp)
                                    )
                                }
                            }
                        }
                    }
                }
                DownloadState.Loading -> {
                    Column(
                        modifier = modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        LoadingAnimation3()
                    }
                }
                DownloadState.Error -> {
                    Column(
                        modifier = modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colors.background),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(id = R.string.loading_error),
                            fontSize = 26.sp,
                            textAlign = TextAlign.Center
                        )
                        Button(onClick = {}) {
                            Text(
                                text = stringResource(id = R.string.try_again),
                                color = Color.White,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }
    }
}