package com.example.sportsapp.presentation.countries_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.example.sportsapp.R
import com.example.sportsapp.common.LocalDataProvider

@Composable
fun CountriesScreen(
    modifier: Modifier = Modifier,
    toTeamsScreen: (String) -> Unit
) {
    val countriesList = LocalDataProvider.getCountriesList()

    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            modifier = modifier,
            bottomBar = {}
        ) { padding ->
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                Text(text = "Choose country", fontSize = 26.sp, modifier = Modifier.padding(20.dp))
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(150.dp),
                    verticalArrangement = Arrangement.spacedBy(25.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(countriesList.size) { item ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                                .clickable { toTeamsScreen(countriesList[item].id) }
                        ) {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(countriesList[item].image)
                                    .memoryCachePolicy(CachePolicy.ENABLED)
                                    .diskCachePolicy(CachePolicy.ENABLED)
                                    .crossfade(true)
                                    .build(),
                                contentDescription = countriesList[item].name,
                                error = painterResource(R.drawable.error_image),
                                placeholder = painterResource(R.drawable.loading_img),
                                contentScale = ContentScale.FillBounds,
                                modifier = Modifier
                                    .size(160.dp)
                                    .align(Alignment.TopCenter)
                            )
                            Text(
                                text = countriesList[item].name,
                                fontSize = 20.sp,
                                modifier = Modifier
                                    .align(Alignment.BottomCenter)
                                    .padding(8.dp)
                            )
                        }
                    }
                }
            }

        }
    }
}