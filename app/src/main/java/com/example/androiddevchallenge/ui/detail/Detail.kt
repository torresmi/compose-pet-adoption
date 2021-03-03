package com.example.androiddevchallenge.ui.detail

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.Chameleon
import com.example.androiddevchallenge.data.Gender
import com.example.androiddevchallenge.data.Species
import com.example.androiddevchallenge.ui.PrimaryText
import com.example.androiddevchallenge.ui.SecondaryText
import com.example.androiddevchallenge.ui.theme.AppTheme
import java.util.*

@Composable
fun Detail(chameleon: Chameleon, navigateUp: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(chameleon.name) },
                navigationIcon = {
                    IconButton(onClick = navigateUp) {
                        Icon(Icons.Default.ArrowBack, contentDescription = null)
                    }
                },
                elevation = 0.dp
            )
        },
        content = {
            Column {
                Header(chameleon)
                Details(chameleon)
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Icon(Icons.Default.Favorite, contentDescription = null, tint = Color.White)
            }
        },
        floatingActionButtonPosition = FabPosition.End
    )
}

@Composable
private fun Details(chameleon: Chameleon) {
    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {

            InfoItem(
                Icons.Default.VerifiedUser,
                R.string.detail_species_title,
                chameleon.species.prettyPrint()
            )
            Divider()

            val genderIcon = when (chameleon.gender) {
                Gender.MALE -> Icons.Default.Male
                Gender.FEMALE -> Icons.Default.Female
            }
            InfoItem(
                genderIcon,
                R.string.detail_gender_title,
                chameleon.gender.prettyPrint()
            )
            Divider()

            InfoItem(
                Icons.Default.PushPin,
                R.string.detail_location_title,
                chameleon.location
            )
            Divider()

        }
    }
}

@Composable
private fun Header(chameleon: Chameleon) {
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth(),
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Image(
                painter = painterResource(id = chameleon.image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(16.dp),
                contentScale = ContentScale.FillHeight,
            )
        }
    }
}

@Composable
private fun InfoItem(icon: ImageVector, @StringRes titleResId: Int, value: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(16.dp)
    ) {
        Icon(
            icon,
            contentDescription = null,
            modifier = Modifier
                .alpha(0.8f)
                .padding(end = 8.dp)
        )
        Column {
            PrimaryText(
                stringResource(id = titleResId),
                style = TextStyle(fontWeight = FontWeight.SemiBold)
            )
            SecondaryText(value)
        }
    }
}

// Previews

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
private fun LightPreview() {
    AppTheme {
        Detail(
            chameleon = Chameleon(
                id = UUID.randomUUID(),
                name = "Frodo",
                location = "Rivendell",
                species = Species.PANTHER,
                image = R.drawable.ic_chameleon_one,
                gender = Gender.MALE
            ),
            navigateUp = {}
        )
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
private fun DarkPreview() {
    AppTheme(darkTheme = true) {
        Detail(
            chameleon = Chameleon(
                id = UUID.randomUUID(),
                name = "Frodo",
                location = "Rivendell",
                species = Species.PANTHER,
                image = R.drawable.ic_chameleon_one,
                gender = Gender.FEMALE
            ),
            navigateUp = {}
        )
    }
}
