package com.example.group12.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.group12.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBar(navController: NavController, username: String?) {
    val context = LocalContext.current


    Row(
        modifier = Modifier
            .fillMaxWidth(),// 填充父级的宽度
//            .padding(8.dp), // 添加内边距
        horizontalArrangement = Arrangement.SpaceBetween, // 水平方向上平均分布
    ) {
        
        // 第一个图片按钮
        Image(
            painter = painterResource(id = R.drawable.historybutton), // 你的按钮图片资源
            contentDescription = "按钮1",
            modifier = Modifier
                .weight(1f) // 平均分布
                .border(1.dp, Color.Black, RoundedCornerShape(8.dp)) // 边框
                .clickable {
                    navController.navigate("dataActivity")
                }
        )
        Spacer(modifier = Modifier.width(4.dp)) // 添加间隔

        // 第二个图片按钮
        Image(
            painter = painterResource(id = R.drawable.helpbutton), // 你的按钮图片资源
            contentDescription = "按钮2",
            modifier = Modifier
                .weight(1f) // 平均分布
                .border(1.dp, Color.Black, RoundedCornerShape(8.dp)) // 边框
                .clickable {
                    navController.navigate("actionActivity")
                }
        )
        Spacer(modifier = Modifier.width(4.dp)) // 添加间隔

        // 第三个图片按钮
        Image(
            painter = painterResource(id = R.drawable.settingbutton), // 你的按钮图片资源
            contentDescription = "按钮3",
            modifier = Modifier
                .size(120.dp, 72.dp)
                .border(1.dp, Color.Black, RoundedCornerShape(8.dp)) // 边框
                .clickable {
                    navController.navigate("settingScreen/${username}")
                }
        )
    }
}
