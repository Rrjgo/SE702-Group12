package com.example.group12

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.group12.component.BottomBar
import com.example.group12.component.SubtitleSection
import com.example.group12.models.HistoryRecordItem
import com.example.group12.models.PressureStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DataActivity(navController: NavController) {

    val history = listOf(
        HistoryRecordItem("08/10/2023", PressureStatus.HIGH, "Location 1"),
        HistoryRecordItem("07/10/2023", PressureStatus.MEDIUM, "Location 2"),
    )


    Column {
        // 添加TopAppBar
        TopAppBar(
            title = { Text("History") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "返回")
                }
            }
        )

        Column(
            modifier = Modifier
                .padding(16.dp) // 外部的padding
                .border(1.dp, Color.Black, RoundedCornerShape(18.dp)) // 边框
                .align(Alignment.Start) // 左对齐
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 50.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "History",
                    style = MaterialTheme.typography.subtitle1.copy(fontSize = 24.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))

                HistorySubtitle()
            }
            PressureHistoryList(history)
        }

        Column(
            modifier = Modifier
                .padding(16.dp) // 外部的padding
                .border(1.dp, Color.Black, RoundedCornerShape(18.dp)) // 边框
                .align(Alignment.Start) // 左对齐
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 50.dp, vertical = 8.dp) // 左右和上下的内部padding
            ) {
                Text(
                    text = "Location History",
                    style = MaterialTheme.typography.subtitle1.copy(fontSize = 24.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))

                LocationHistorySubtitle()
            }
            LocationHistoryList(history)
        }

        Spacer(modifier = Modifier.weight(1f))
        BottomBar(navController, null)

    }
}

@Composable
fun HistorySubtitle() {
    SubtitleSection(
        title = "Comprehensive evaluation",
    )
}

@Composable
fun LocationHistorySubtitle() {
    SubtitleSection(
        title = "Display the longest stay location",
    )
}


@Composable
fun PressureHistoryList(history: List<HistoryRecordItem>) {
    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
            .border(1.dp, Color.Black)
            .fillMaxWidth()
    ) {
        items(history) { record ->
            PressureHistoryItem(record)
            Divider(color = Color.Gray)
        }
    }
}

@Composable
fun PressureHistoryItem(record: HistoryRecordItem) {
    Row(
        modifier = Modifier
            .clickable {}
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = record.date, style = MaterialTheme.typography.body1)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            PressureStatusDot(pressureStatus = record.pressureStatus)
        }
    }
}


@Composable
fun PressureStatusDot(pressureStatus: PressureStatus) {
    val color = when (pressureStatus) {
        PressureStatus.HIGH -> Color.Red
        PressureStatus.MEDIUM -> Color.Yellow
        PressureStatus.LOW -> Color.Green
    }
    Box(
        modifier = Modifier
            .size(16.dp)
            .background(color, CircleShape)
    )
}

@Composable
fun LocationHistoryList(history: List<HistoryRecordItem>) {
    LazyColumn(
        modifier = Modifier
            .padding(16.dp)
            .border(1.dp, Color.Black)
            .fillMaxWidth()
    ) {
        items(history) { record ->
            LocationHistoryItem(record)
            Divider(color = Color.Gray)
        }
    }
}

@Composable
fun LocationHistoryItem(record: HistoryRecordItem) {
    Row(
        modifier = Modifier
            .clickable {}
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = record.date, style = MaterialTheme.typography.body1)
        Text(text = record.location, style = MaterialTheme.typography.body1)
    }
}
