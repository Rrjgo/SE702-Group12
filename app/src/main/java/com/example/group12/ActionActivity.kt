package com.example.group12

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.group12.component.BottomBar
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActionActivity(navController: NavController) {
    // List of image resource IDs
    val imageOptions = listOf(
        R.drawable.action1,
        R.drawable.action2
    )

    // Select a random image
    val selectedImageResId = remember {
        imageOptions[Random.nextInt(imageOptions.size)]
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        //TopAppBar
        TopAppBar(
            title = { Text("Action") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "返回")
                }
            }
        )

        Image(
            painter = painterResource(id = selectedImageResId),
            contentDescription = "Action Image",
            modifier = Modifier
                .weight(1f) // This will make the Image take all available space, pushing the BottomBar to the bottom
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        BottomBar(navController, null)
    }
}

