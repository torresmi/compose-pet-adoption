package com.example.androiddevchallenge.ui.detail

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.Chameleon
import com.example.androiddevchallenge.data.Species
import com.example.androiddevchallenge.ui.theme.MyTheme
import java.util.*

@Composable
fun Detail(chameleon: Chameleon, navigateUp: () -> Unit) {
    Surface {

    }
}

// Previews

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
private fun LightPreview() {
    MyTheme {
        Detail(
            chameleon = Chameleon(
                id = UUID.randomUUID(),
                name = "Frodo",
                location = "Rivendell",
                species = Species.PANTHER,
                image = R.drawable.ic_chameleon_one,
            ),
            navigateUp = {}
        )
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
private fun DarkPreview() {
    MyTheme(darkTheme = true) {
        Detail(
            chameleon = Chameleon(
                id = UUID.randomUUID(),
                name = "Frodo",
                location = "Rivendell",
                species = Species.PANTHER,
                image = R.drawable.ic_chameleon_one,
            ),
            navigateUp = {}
        )
    }
}
