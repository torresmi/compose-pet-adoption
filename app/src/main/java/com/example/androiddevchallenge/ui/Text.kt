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

import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.TextStyle

@Composable
fun PrimaryText(text: String, modifier: Modifier = Modifier, style: TextStyle = LocalTextStyle.current) {
    Text(
        text = text,
        modifier = modifier.alpha(0.87f),
        style = style,
    )
}

@Composable
fun SecondaryText(text: String, modifier: Modifier = Modifier, style: TextStyle = LocalTextStyle.current,) {
    Text(
        text = text,
        modifier = modifier.alpha(0.6f),
        style = style,
    )
}
