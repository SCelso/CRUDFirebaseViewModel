package com.example.crudfirebaseviewmodel.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.crudfirebaseviewmodel.R

@Composable
fun BackgroundImage() {
    Image(
        painter = painterResource(id = R.drawable.farmacia_1),
        contentDescription = "logo_farmacia",
        modifier = Modifier
            .fillMaxSize())
}