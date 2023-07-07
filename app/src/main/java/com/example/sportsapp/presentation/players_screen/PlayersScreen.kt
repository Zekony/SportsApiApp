package com.example.sportsapp.presentation.players_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.sportsapp.R
import com.example.sportsapp.domain.model.Player
import com.example.sportsapp.presentation.util.DownloadState
import com.example.sportsapp.presentation.util.LoadingAnimation3

@Composable
fun PlayersScreen(
    id: String,
    modifier: Modifier = Modifier,
    viewModel: PlayersScreenViewModel = hiltViewModel(),
    toBrowserClick: (String) -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getSquadById(id)
        viewModel.getTeamById(id)
    }
    val state = viewModel.state.collectAsStateWithLifecycle().value

    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold { padding ->
            when (state.downloadState) {
                DownloadState.Success -> {
                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxSize()
                    ) {
                        androidx.compose.material.Card(
                            modifier = modifier
                                .border(BorderStroke(2.dp, MaterialTheme.colors.primary))
                                .fillMaxWidth()
                        ) {
                            Row(
                                modifier = modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.Start,
                                    modifier = Modifier
                                        .weight(1f)
                                        .background(MaterialTheme.colors.surface)
                                ) {
                                    Text(
                                        text = state.team.name,
                                        fontSize = 32.sp,
                                        modifier = Modifier.align(Alignment.CenterHorizontally)
                                    )
                                    Spacer(modifier = Modifier.height(20.dp))

                                    Text(
                                        text = "Coach",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 26.sp
                                    )
                                    Text(
                                        text = if (state.coach.name.isEmpty()) "-" else
                                            state.coach.name.replaceFirstChar { it.uppercase() },
                                        fontSize = 26.sp,
                                        color = Color.Blue,
                                        textDecoration = TextDecoration.Underline,
                                        modifier = Modifier.clickable { toBrowserClick(state.coach.name) }
                                    )
                                }
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(state.team.img)
                                        .memoryCachePolicy(CachePolicy.ENABLED)
                                        .diskCachePolicy(CachePolicy.ENABLED)
                                        .crossfade(true)
                                        .build(),
                                    contentDescription = state.team.name,
                                    error = painterResource(R.drawable.error_image),
                                    placeholder = painterResource(R.drawable.loading_img),
                                    contentScale = ContentScale.FillBounds,
                                    modifier = Modifier
                                        .size(150.dp)
                                        .padding(20.dp)
                                        .weight(1f)
                                )
                            }
                        }
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.fillMaxWidth()) {
                                Spacer(modifier = Modifier.height(30.dp))
                                LazyColumn(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalArrangement = Arrangement.spacedBy(15.dp)
                                ) {
                                    items(state.playersList.size) { item ->
                                        TeamMember(
                                            player = state.playersList[item],
                                            toBrowserClick = toBrowserClick
                                        )
                                    }
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
                            .fillMaxSize(),
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

@Composable
fun TeamMember(
    player: Player, modifier: Modifier = Modifier, toBrowserClick: (String) -> Unit
) {
    androidx.compose.material.Card(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(6.dp)
            .border(BorderStroke(2.dp, MaterialTheme.colors.primary))
            .clickable { toBrowserClick(player.name) }
    ) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(player.img)
                    .crossfade(true)
                    .build(),
                error = painterResource(id = R.drawable.error_image),
                placeholder = painterResource(id = R.drawable.loading_img),
                contentDescription = player.name,
                contentScale = ContentScale.Crop,
                alignment = Alignment.TopCenter,
                modifier = Modifier
                    .size(100.dp)
            )
            Text(
                text = player.name.replaceFirstChar { it.uppercase() },
                fontWeight = FontWeight.Medium,
                color = Color.Blue,
                textDecoration = TextDecoration.Underline,
                fontSize = 20.sp,
                modifier = modifier
                    .padding(start = 15.dp)
                    .weight(1f)

            )
            Text(
                text = "-   " + player.position.uppercase(),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = modifier
                    .padding(start = 15.dp)
                    .weight(0.5f)
            )
        }
    }
}