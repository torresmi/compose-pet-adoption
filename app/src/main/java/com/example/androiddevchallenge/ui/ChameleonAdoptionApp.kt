package com.example.androiddevchallenge.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.NavigationActions
import com.example.androiddevchallenge.Route
import com.example.androiddevchallenge.data.CHAMELEONS
import com.example.androiddevchallenge.ui.detail.Detail
import com.example.androiddevchallenge.ui.home.Home
import com.example.androiddevchallenge.ui.theme.AppTheme
import java.util.*

@Composable
fun ChameleonAdoptionApp(darkTheme: Boolean) {
    val navController = rememberNavController()
    val navActions = remember(navController) { NavigationActions(navController) }

    AppTheme(darkTheme = darkTheme) {
        NavHost(navController, startDestination = Route.Home.path) {
            composable(Route.Home.path) {
                Home(
                    darkTheme,
                    CHAMELEONS.values.toList(),
                    openDetail = navActions.openDetail,
                )
            }
            composable(
                Route.Detail.path,
                arguments = listOf(
                    navArgument(Route.Detail.idArgument) { type = NavType.StringType },
                )
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getString(Route.Detail.idArgument)
                requireNotNull(id)
                val chameleon = CHAMELEONS.getValue(UUID.fromString(id))

                Detail(
                    chameleon = chameleon,
                    navActions.navigateUp
                )
            }
        }
    }
}
