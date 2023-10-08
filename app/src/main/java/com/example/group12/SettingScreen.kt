package com.example.group12

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.group12.component.BottomBar
import com.example.group12.component.SubtitleSectionClickable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(navController: NavController, username: String?) {

    Column {
        // 添加TopAppBar
        TopAppBar(
            title = { Text("Setting") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "返回")
                }
            }
        )
        // ... 其他UI组件

// 用户信息区域
        Row(
            modifier = Modifier.padding(16.dp), // 添加内边距
            verticalAlignment = Alignment.CenterVertically // 垂直居中对齐
        ) {
            // 头像图片
            Image(
                painter = painterResource(id = R.drawable.profile), // 使用你的图片资源
                contentDescription = "User Avatar",
                modifier = Modifier.size(48.dp) // 设置图片大小
            )
            // 用户名和欢迎信息
            Column(
                modifier = Modifier.padding(start = 8.dp) // 添加左边距
            ) {
                Text(text = "Username: $username")
                Text(text = "Welcome to the group12 study!")
            }
        }
        Spacer(modifier = Modifier.height(16.dp)) // 调整这个值来增加或减少空间

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
                    text = "Settings",
                    style = MaterialTheme.typography.subtitle1.copy(fontSize = 24.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))



                val (isOn, setIsOn) = remember { mutableStateOf(true) }
                NotificationsSubtitle(isOn = isOn, onToggle = { setIsOn(!isOn) })



                var isDropdownExpanded by remember { mutableStateOf(false) }
                var selectedInterval by remember { mutableStateOf("2 hour") }
                DataCollectionIntervalSubtitle(
                    isDropdownExpanded = isDropdownExpanded,
                    onDropdownExpandRequest = { isDropdownExpanded = true },
                    onDropdownDismissRequest = { isDropdownExpanded = false },
                    selectedInterval = selectedInterval,
                    onIntervalSelect = { newInterval ->
                        selectedInterval = newInterval
                    }
                )

                AuthorizationSubtitle(onClick = { println("Authorization to Collect Data clicked") })
            }
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
                    text = "Help",
                    style = MaterialTheme.typography.subtitle1.copy(fontSize = 24.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Spacer(modifier = Modifier.height(4.dp)) // 添加一些间隔


                HelpSubtitle(onClick = { println("Help clicked") })


                SupportSubtitle(onClick = { println("Support clicked") })
            }

        }

        Spacer(modifier = Modifier.weight(1f))

        BottomBar(navController, username)

    }
}


@Composable
fun NotificationsSubtitle(isOn: Boolean, onToggle: () -> Unit) {
    SubtitleSectionClickable(
        title = "Notifications",
        control = { TextToggle(isOn = isOn, onToggle = onToggle) }
    )
}

@Composable
fun AuthorizationSubtitle(onClick: () -> Unit) {
    SubtitleSectionClickable(
        title = "Authorization to Collect Data",
        onClick = onClick
    )
}



@Composable
fun TextToggle(isOn: Boolean, onToggle: () -> Unit) {
    Text(
        text = if (isOn) "ON" else "OFF",
        modifier = Modifier.clickable(onClick = onToggle)
    )
}


@Composable
fun DataCollectionIntervalSubtitle(
    isDropdownExpanded: Boolean,
    onDropdownExpandRequest: () -> Unit,
    onDropdownDismissRequest: () -> Unit,
    selectedInterval: String,
    onIntervalSelect: (String) -> Unit
) {
    SubtitleSectionClickable(
        title = "Data Collection Interval",
        control = {
            IntervalDropdown(
                isDropdownExpanded = isDropdownExpanded,
                onDropdownExpandRequest = onDropdownExpandRequest,
                onDropdownDismissRequest = onDropdownDismissRequest,
                selectedInterval = selectedInterval,
                onIntervalSelect = onIntervalSelect
            )
        }
    )
}

@Composable
fun IntervalDropdown(
    isDropdownExpanded: Boolean,
    onDropdownExpandRequest: () -> Unit,
    onDropdownDismissRequest: () -> Unit,
    selectedInterval: String,
    onIntervalSelect: (String) -> Unit
) {
    Text(
        text = selectedInterval,
        style = MaterialTheme.typography.body1,
        modifier = Modifier.clickable(onClick = onDropdownExpandRequest)
    )
    DropdownMenu(
        expanded = isDropdownExpanded,
        onDismissRequest = onDropdownDismissRequest
    ) {
        val intervals = listOf("1 hour", "2 hours", "4 hours", "8 hours", "12 hours")
        intervals.forEach { interval ->
            DropdownMenuItem(onClick = { onIntervalSelect(interval) }) {
                Text(text = interval)
            }
        }
    }
}


@Composable
fun HelpSubtitle(onClick: () -> Unit) {
    SubtitleSectionClickable(
        title = "Help",
        onClick = onClick
    )
}

@Composable
fun SupportSubtitle(onClick: () -> Unit) {
    SubtitleSectionClickable(
        title = "Support",
        onClick = onClick
    )
}
