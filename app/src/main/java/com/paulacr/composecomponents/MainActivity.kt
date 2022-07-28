package com.paulacr.composecomponents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.paulacr.composecomponents.ui.theme.ComposeComponentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeComponentsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Gray,
                    contentColor = Color.Gray
                ) {
                    CalendarList()
                }
            }
        }
    }


}

@Composable
private fun CalendarList() {
    LazyColumn {
        items(10) { index ->
            Row(modifier = Modifier.wrapContentSize(), verticalAlignment = Alignment.CenterVertically) {
                CalendarIcon(size = 120.dp, day = "16", month = "July")
                Text(
                    text = "List item $index",
                    color = Color.White,
                    modifier = Modifier.fillMaxHeight().padding(top = 36.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!", color =  Color.Gray)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeComponentsTheme {
//        Greeting("Android")
        CalendarList()
    }
}