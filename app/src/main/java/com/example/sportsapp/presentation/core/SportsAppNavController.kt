package com.example.sportsapp.presentation.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.sportsapp.presentation.countries_screen.CountriesScreen
import com.example.sportsapp.presentation.players_screen.PlayersScreen
import com.example.sportsapp.presentation.teams_screen.TeamsScreen
import com.example.sportsapp.presentation.webview_screen.WebViewScreen

@Composable
fun SportsAppNavController() {
    val navController = rememberNavController()
    val urlWord = remember { mutableStateOf("") }
    NavHost(navController = navController, startDestination = "countries") {
        composable(
            route = "countries"
        ) {
            CountriesScreen(toTeamsScreen = { navController.navigate("teams/${it}") })
        }
        composable(
            route = "teams/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            TeamsScreen(
                id = entry.arguments?.getString("id")!!,
                toPlayersScreen = { navController.navigate("players/${it}") }
            )
        }
        composable(
            route = "players/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) { entry ->
            PlayersScreen(
                id = entry.arguments?.getString("id")!!,
                toBrowserClick = { word ->
                    urlWord.value = word
                    navController.navigate("webview")
                }
            )
        }
        composable(
            route = "webview"
        ) {
            WebViewScreen(
                urlWord = urlWord.value
            )
        }
    }
}