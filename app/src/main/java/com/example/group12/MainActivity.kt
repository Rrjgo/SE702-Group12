@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.group12

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.group12.ui.theme.Group12Theme
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Group12Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController() // 创建一个NavController
                    NavHost(navController = navController, startDestination = "mainScreen") {
                        composable("mainScreen") {
                            MainScreen(navController) // 传递NavController到MainScreen composable
                        }
                        composable("loginScreen") {
                            LoginScreen(navController)
                        }
                        // 添加注册和帮助屏幕的composable
                        composable("registerScreen") {
                            RegisterScreen(navController)
                        }
                        composable("helpScreen") {
                            HelpScreen(navController)
                        }
                        composable("settingScreen/{username}") {backStackEntry ->
                            val arguments = backStackEntry.arguments
                            val username = arguments?.getString("username")
                            SettingScreen(navController, username)
                        }
                        composable("actionActivity") {
                            ActionActivity(navController)
                        }

                        composable("dataActivity") {
                            DataActivity(navController)
                        }

//                        // 添加新的用户页面composable
//                        composable("user_page/{username}") { backStackEntry ->
//                            val arguments = backStackEntry.arguments
//                            val username = arguments?.getString("username")
//                            UserPage(navController)
//                        }

                }
            }
        }
    }
}



@Composable
fun MainScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Greeting("Mental Health Questionnaire")





        // 在这里添加图片
        Image(
            painter = painterResource(id = R.drawable.home),
            contentDescription = "描述",
            modifier = Modifier
                .fillMaxWidth() // 设置图片宽度等于屏幕宽度
                .scale(1.1f)
                .padding(top = 36.dp, bottom = 32.dp), // 在图片上下方添加外边距
            contentScale = ContentScale.FillWidth // 使图片的宽度拉伸以填充屏幕宽度
        )

        Spacer(modifier = Modifier.height(105.dp)) // 调整为您需要的高度


        // 按钮行
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.Black, RoundedCornerShape(8.dp)) // 给按钮添加1dp黑色边框
                .padding(8.dp), // 为按钮行添加额外的padding
            horizontalArrangement = Arrangement.SpaceBetween // 按钮之间平均分配空间
        ){
            Image(
                painter = painterResource(id = R.drawable.loginbutton),
                contentDescription = "Login",
                modifier = Modifier.clickable {
                    navController.navigate("loginScreen") // 在这里导航到登录页面
                }
                    .background(Color.Transparent)
            )
            Image(
                painter = painterResource(id = R.drawable.registerbutton),
                contentDescription = "Register",
                modifier = Modifier.clickable {
                    navController.navigate("registerScreen")
                }
                    .background(Color.Transparent)
            )
            Image(
                painter = painterResource(id = R.drawable.helpbutton),
                contentDescription = "Help",
                modifier = Modifier.clickable {
                    navController.navigate("helpScreen")
                }
                    .background(Color.Transparent)
            )

        }



    }
}


@Composable
fun LoginScreen(navController: NavController) {
        // 创建一个可观察的状态变量来存储用户名和密码
        val username = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }

        // 预定义的用户名和密码
        val validCredentials = mapOf("1" to "1", "2" to "2", "3" to "3")
        // 创建一个状态来观察登录尝试是否失败
        val loginFailed = remember { mutableStateOf(false) }

        // 获取当前的上下文
        val context = LocalContext.current
        // 创建一个 CoroutineScope
        val coroutineScope = rememberCoroutineScope()


        Column(
            modifier = Modifier.fillMaxSize(), // 使列占满屏幕
            verticalArrangement = Arrangement.Top, // 垂直居中内容
            horizontalAlignment = Alignment.CenterHorizontally // 水平居中内容
        ) {
            // 添加TopAppBar
            TopAppBar(
                title = { Text("Login") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "返回")
                    }
                }
            )
            // 添加图片
            Image(
                painter = painterResource(id = R.drawable.bg1),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().height(200.dp),
                contentScale = ContentScale.FillWidth
            )

            // 用户名输入框
            TextField(
                value = username.value,
                onValueChange = { newValue -> username.value = newValue },
                label = { Text("Username") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            // 密码输入框
            TextField(
                value = password.value,
                onValueChange = { newValue -> password.value = newValue },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 32.dp)
            )

            // 按钮列
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                // 登录按钮
                Box(
                    modifier = Modifier
                        .width(300.dp)
                        .height(100.dp) // 设置高度
                        .align(Alignment.CenterHorizontally)
                        .background(Color.Transparent),
                    contentAlignment = Alignment.Center // 设置内容居中
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.loginbutton2),
                        contentDescription = "Login",
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {

                                // 检查用户名和密码
                                if (validCredentials[username.value] == password.value) {

                                    // 验证通过，导航到下一个页面
                                    navController.navigate("settingScreen/${username.value}")
                                } else {
                                    // 设置登录失败状态为 true
                                    loginFailed.value = true
                                    // 在协程中显示 Toast 消息
                                    coroutineScope.launch {
                                        Toast.makeText(
                                            context,
                                            "Invalid credentials!",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                    loginFailed.value = false
                                }
                                // 在这里导航到登录页面
                            }
                    )
                }

                // 在按钮之间添加额外的空间
                Spacer(modifier = Modifier.height(70.dp)) // 调整为您需要的高度

                // 注册按钮
                Box(
                    modifier = Modifier
                        .width(300.dp)
                        .height(100.dp) // 设置高度
                        .align(Alignment.CenterHorizontally)
                        .background(Color.Transparent),
                    contentAlignment = Alignment.Center // 设置内容居中
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.registerbutton2),
                        contentDescription = "Register",
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                // 在这里导航到注册页面
                            }
                    )
                }
            }
        }
    }






    @Composable
    fun RegisterScreen(navController: NavController) {
    // 创建一个可观察的状态变量来存储用户名和密码
        val username = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }

        Column(
            modifier = Modifier.fillMaxSize(), // 使列占满屏幕
            verticalArrangement = Arrangement.Top, // 垂直居中内容
            horizontalAlignment = Alignment.CenterHorizontally // 水平居中内容
        ) {
            // 添加TopAppBar
            TopAppBar(
                title = { Text("Register") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "返回")
                    }
                }
            )
            // 添加图片
            Image(
                painter = painterResource(id = R.drawable.bg1),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().height(200.dp),
                contentScale = ContentScale.FillWidth
            )

            // 用户名输入框
            TextField(
                value = username.value,
                onValueChange = { newValue -> username.value = newValue },
                label = { Text("Username") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            // 密码输入框
            TextField(
                value = password.value,
                onValueChange = { newValue -> password.value = newValue },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 32.dp)
            )

            // 按钮列
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                // 登录按钮
                Box(
                    modifier = Modifier
                        .width(300.dp)
                        .height(100.dp) // 设置高度
                        .align(Alignment.CenterHorizontally)
                        .background(Color.Transparent),
                    contentAlignment = Alignment.Center // 设置内容居中
                ) {

                }

                // 在按钮之间添加额外的空间
                Spacer(modifier = Modifier.height(70.dp)) // 调整为您需要的高度

                // 注册按钮
                Box(
                    modifier = Modifier
                        .width(300.dp)
                        .height(100.dp) // 设置高度
                        .align(Alignment.CenterHorizontally)
                        .background(Color.Transparent),
                    contentAlignment = Alignment.Center // 设置内容居中
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.registerbutton2),
                        contentDescription = "Register",
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                // 在这里导航到注册页面
                            }
                    )
                }
            }
        }
    }

//    @Composable
//    fun UserPage(navController: NavController) {
//        val backStackEntry = navController.currentBackStackEntry
//        val username = backStackEntry?.arguments?.getString("username")
//        val completion = 80 // 这个是问卷完成度，你可以从其他地方获取这个值
//        val context = LocalContext.current
//
//        Column {
//            // 添加TopAppBar
//            TopAppBar(
//                title = { Text("Home") },
//                navigationIcon = {
//                    IconButton(onClick = { navController.popBackStack() }) {
//                        Icon(Icons.Filled.ArrowBack, contentDescription = "返回")
//                    }
//                }
//            )
//            // 用户信息区域
//            Row(
//                modifier = Modifier.padding(16.dp), // 添加内边距
//                verticalAlignment = Alignment.CenterVertically // 垂直居中对齐
//            ) {
//                // 头像图片
//                Image(
//                    painter = painterResource(id = R.drawable.profile), // 使用你的图片资源
//                    contentDescription = "User Avatar",
//                    modifier = Modifier.size(48.dp) // 设置图片大小
//                )
//                // 用户名和欢迎信息
//                Column(
//                    modifier = Modifier.padding(start = 8.dp) // 添加左边距
//                ) {
//                    Text(text = "Username: $username")
//                    Text(text = "Welcome to the group12 study!")
//                }
//            }
//            Spacer(modifier = Modifier.height(16.dp)) // 调整这个值来增加或减少空间
//
//            // 添加图片按钮
//            Image(
//                painter = painterResource(id = R.drawable.questionnairebutton), // 你的按钮图片资源
//                contentDescription = "按钮",
//                modifier = Modifier
//                    .fillMaxWidth() // 填充父级的宽度
//                    .padding(top = 0.dp, start = 16.dp, end = 16.dp, bottom = 0.dp)
//                    .align(Alignment.CenterHorizontally)
//                    .clickable { /* 处理点击事件 */ }
//
//            )
//            // 添加问卷完成度文本
//            Column(
//                modifier = Modifier
//                    .padding(16.dp) // 外部的padding
//                    .border(1.dp, Color.Black, RoundedCornerShape(18.dp)) // 边框
//                    .align(Alignment.Start) // 左对齐
//            ) {
//                Column(
//                    modifier = Modifier.padding(horizontal = 50.dp, vertical = 8.dp) // 左右和上下的内部padding
//                ) {
//                    Text(text = "Questionnaire completion")
//                    Spacer(modifier = Modifier.height(4.dp)) // 添加一些间隔
//                    Text(text = "$completion%")
//                    Spacer(modifier = Modifier.height(4.dp)) // 添加一些间隔
//                    Text(text = "You have completed $completion%of the questionnaire, keep up the good work!")
//                }
//            }
//
//            // 下方添加三个图片作为按钮
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth() // 填充父级的宽度
//                    .padding(16.dp), // 添加内边距
//                horizontalArrangement = Arrangement.SpaceBetween, // 水平方向上平均分布
//            ) {
//                // 第一个图片按钮
//                Image(
//                    painter = painterResource(id = R.drawable.exitbutton), // 你的按钮图片资源
//                    contentDescription = "按钮1",
//                    modifier = Modifier
//                        .weight(1f) // 平均分布
//                        .border(1.dp, Color.Black, RoundedCornerShape(8.dp)) // 边框
//                        .clickable {
//                            if (context is Activity) {
//                                context.finish()
//                            }
//                        }
//                )
//                Spacer(modifier = Modifier.width(4.dp)) // 添加间隔
//
//                // 第二个图片按钮
//                Image(
//                    painter = painterResource(id = R.drawable.historybutton), // 你的按钮图片资源
//                    contentDescription = "按钮2",
//                    modifier = Modifier
//                        .weight(1f) // 平均分布
//                        .border(1.dp, Color.Black, RoundedCornerShape(8.dp)) // 边框
//                        .clickable {
//                            navController.navigate("actionActivity")
//                        }
//                )
//                Spacer(modifier = Modifier.width(4.dp)) // 添加间隔
//
//                // 第三个图片按钮
//                Image(
//                    painter = painterResource(id = R.drawable.helpbutton), // 你的按钮图片资源
//                    contentDescription = "按钮3",
//                    modifier = Modifier
//                        .weight(1f) // 平均分布
//                        .border(1.dp, Color.Black, RoundedCornerShape(8.dp)) // 边框
//                        .clickable {
//                            navController.navigate("settingScreen/${username}")}
//                )
//            }
//
//// ... 其他UI组件
//
//
//        }
//    }



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "Welcome to the $name!",
            modifier = modifier
                .fillMaxWidth() // 填充父级的宽度
                .padding(top = 16.dp, bottom = 16.dp) // 设置上下的 padding
                .wrapContentSize(Alignment.Center) // 文本内容居中
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Group12Theme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Greeting("Mental Health Questionnaire")

            // 在这里添加图片
            Image(
                painter = painterResource(id = R.drawable.home),
                contentDescription = "描述",
                modifier = Modifier
                    .fillMaxWidth() // 设置图片宽度等于屏幕宽度
                    .align(Alignment.CenterHorizontally) // 水平居中图片
                    .padding(bottom = 16.dp), // 在图片下方添加16.dp的外边距
                contentScale = ContentScale.FillWidth // 使图片的宽度拉伸以填充屏幕宽度
            )

            // 按钮行
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, Color.Black, RoundedCornerShape(8.dp)) // 给按钮添加1dp黑色边框
                    .padding(8.dp), // 为按钮行添加额外的padding
                horizontalArrangement = Arrangement.SpaceBetween // 按钮之间平均分配空间
            ) {
                // 使用图片作为按钮，并且设置背景为透明
                Image(
                    painter = painterResource(id = R.drawable.loginbutton),
                    contentDescription = "Login",
                    modifier = Modifier.clickable { /*处理按钮点击*/ }
                        .background(Color.Transparent)
                )
                Image(
                    painter = painterResource(id = R.drawable.registerbutton),
                    contentDescription = "Register",
                    modifier = Modifier.clickable { /*处理按钮点击*/ }
                        .background(Color.Transparent)
                )
                Image(
                    painter = painterResource(id = R.drawable.helpbutton),
                    contentDescription = "Help",
                    modifier = Modifier.clickable { /*处理按钮点击*/ }
                        .background(Color.Transparent)
                )
            }
        }
    }
}}
