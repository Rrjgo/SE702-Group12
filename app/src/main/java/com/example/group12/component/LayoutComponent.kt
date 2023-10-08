package com.example.group12.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SubtitleSectionClickable(title: String, control: @Composable (() -> Unit)? = null, onClick: (() -> Unit)? = null) {
    Row(
        modifier = Modifier
            .clickable { onClick?.invoke() }
            .padding(vertical = 6.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title, style = MaterialTheme.typography.body1)
        control?.invoke()
    }
    Divider(color = Color.Black, thickness = 1.dp, modifier = Modifier.fillMaxWidth().padding(top = 2.dp))
}

@Composable
fun SubtitleSection(title: String, control: @Composable (() -> Unit)? = null) {
    Row(
        modifier = Modifier
            .padding(vertical = 6.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title, style = MaterialTheme.typography.body1)
        control?.invoke()
    }
    Divider(color = Color.Black, thickness = 1.dp, modifier = Modifier.fillMaxWidth().padding(top = 2.dp))
}