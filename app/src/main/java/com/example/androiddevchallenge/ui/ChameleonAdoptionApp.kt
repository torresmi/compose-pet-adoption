/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
import java.util.UUID

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
