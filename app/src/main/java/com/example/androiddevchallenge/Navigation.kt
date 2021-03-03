package com.example.androiddevchallenge

import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import java.util.*

sealed class Route(val path: String) {
    object Home : Route("home")
    object Detail : Route("detail/{id}") {
        val idArgument = "id"
        fun pathWithId(id: UUID) = path.replace("{id}", id.toString())
    }
}


class NavigationActions(navController: NavController) {
    val openDetail: (UUID) -> Unit = { id ->
        navController.navigate(Route.Detail.pathWithId(id))
    }

    val navigateUp: () -> Unit = {
        navController.popBackStack()
    }
}
