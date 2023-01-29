package com.example.crudfirebaseviewmodel.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TextInput(
    text: String,
    onValueChange: (String) -> Unit,
    label: String,

    modifier: Modifier = Modifier,
) {


    OutlinedTextField(
        value = text,
        onValueChange = onValueChange,
        label = { InputLabel(text = label) },
        singleLine = true,

        modifier = modifier.width(350.dp)

    )
}

@Composable
fun InputLabel(text: String/* imageVector: ImageVector*/) {
    Row {
        /*Icon(
            imageVector = imageVector,
            contentDescription = "Arrow Back"
        )
        Spacer(modifier = Modifier.width(8.dp))*/

        Text(text = text)
    }
}