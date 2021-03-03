package com.example.androiddevchallenge.ui.home

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.Chameleon
import com.example.androiddevchallenge.data.Species
import com.example.androiddevchallenge.ui.PrimaryText
import com.example.androiddevchallenge.ui.SecondaryText
import com.example.androiddevchallenge.ui.theme.AppTheme
import com.example.androiddevchallenge.ui.theme.gray200
import com.example.androiddevchallenge.ui.theme.gray700
import java.util.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Home(
    darkTheme: Boolean,
    chameleons: List<Chameleon>,
    openDetail: (UUID) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.home_title)) },
                elevation = 12.dp,
            )
        },
        content = {
            Log.d("Home", it.toString())
            LazyVerticalGrid(
                contentPadding = PaddingValues(
                    start = 4.dp,
                    top = 4.dp,
                    end = 4.dp,
                    bottom = 12.dp,
                ),
                cells = GridCells.Fixed(2),
            ) {
                items(chameleons) { item: Chameleon ->
                    Item(darkTheme, item, openDetail)
                }
            }
        }
    )
}

@Composable
private fun Item(darkTheme: Boolean, item: Chameleon, openDetail: (UUID) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 4.dp,
                end = 4.dp,
                top = 4.dp,
                bottom = 4.dp,
            )
            .clickable { openDetail(item.id) },
    ) {
        Column {
            Image(
                painter = painterResource(id = item.image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp)
                    .padding(8.dp),
                contentScale = ContentScale.FillWidth,
            )

            Divider(color = if (darkTheme) gray700 else gray200)

            PrimaryText(
                text = item.name,
                modifier = Modifier.padding(start = 8.dp, top = 4.dp, end = 8.dp),
                style = TextStyle(fontWeight = FontWeight.Bold),
            )

            SecondaryText(
                text = item.species.prettyPrint(),
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                    .alpha(0.6f),
                style = TextStyle(fontWeight = FontWeight.Light),
            )
        }
    }
}

// Previews

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
private fun LightPreview() {
    AppTheme {
        Home(
            darkTheme = false,
            chameleons = listOf(
                Chameleon(
                    id = UUID.randomUUID(),
                    name = "Frodo",
                    location = "Rivendell",
                    species = Species.PANTHER,
                    image = R.drawable.ic_chameleon_one,
                )
            ),
            openDetail = {}
        )
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
private fun DarkPreview() {
    AppTheme {
        Home(
            darkTheme = true,
            chameleons = listOf(
                Chameleon(
                    id = UUID.randomUUID(),
                    name = "Frodo",
                    location = "Rivendell",
                    species = Species.PANTHER,
                    image = R.drawable.ic_chameleon_one,
                )
            ),
            openDetail = {}
        )
    }
}
