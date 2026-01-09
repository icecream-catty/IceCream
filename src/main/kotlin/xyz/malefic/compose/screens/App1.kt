package xyz.malefic.compose.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import xyz.malefic.compose.comps.box.BackgroundBox
import xyz.malefic.compose.comps.text.Headline
import xyz.malefic.ext.any.resolveNull
import xyz.malefic.ext.string.either

@Composable
fun App1(
    id: String,
    name: String?,
) {
    var text by remember { mutableStateOf("Hello, World!") }

    BackgroundBox(contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = CenterHorizontally, verticalArrangement = Arrangement.Center) {
            TextButton(onClick = { text = text.either("Hello, World!", "Hello, Desktop!") }) { Text(text) }
            Headline("ID: $id")
            Headline(name.resolveNull("Name: $name", "Unnamed"))
        }
    }
}
