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
fun SecondaryText(text: String, modifier: Modifier = Modifier, style: TextStyle = LocalTextStyle.current, ) {
    Text(
        text = text,
        modifier = modifier.alpha(0.6f),
        style = style,
    )
}
