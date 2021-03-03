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
package com.example.androiddevchallenge.ui.detail

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.Male
import androidx.compose.material.icons.filled.VerifiedUser
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
import java.util.UUID

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
                Icons.Default.LocationCity,
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
