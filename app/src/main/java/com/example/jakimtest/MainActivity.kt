package com.example.jakimtest

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jakimtest.ui.theme.JAKIMTestTheme
import kotlinx.coroutines.NonCancellable.children
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            JAKIMTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {Card(modifier = Modifier.shadow(10.dp, RectangleShape,)){
                    Column(horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier.padding(10.dp)) {
                        Greeting("Android")
                        ButtonTest()
                    }
                }


                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}
@Composable
fun Build_Prayer_List(prayerTime: PrayerTime) {
    Text(text = "hijri")
    Text(text = prayerTime.date)
    Text(text = prayerTime.day)
    for (item in prayerTime.prayer_times as Hashtable) {
        Card(
            modifier = Modifier
                .shadow(4.dp)
                .fillMaxWidth()
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally,modifier = Modifier.padding(20.dp)) {
                Text(text = item.key.toString().capitalize() + " : " + item.value.toString())
            }

        }
        Divider(color = Color.Blue, thickness = 2.dp, modifier = Modifier.padding(5.dp))
    }
}
fun Get_Prayer_Times():PrayerTime{
    var prayerData:PrayerTime
    var prayerTimeExtract = PrayerTimeExtract()
    prayerData = prayerTimeExtract.processJSON()
    return prayerData

}
@Composable
fun ButtonTest(){

    var Clicked by remember {
        mutableStateOf(false)
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
        Clicked = true}) {
            Text(text = "Submit")
            }
        if(Clicked == true){
            Build_Prayer_List(prayerTime = Get_Prayer_Times())
        }
        }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JAKIMTestTheme {
        Card(){
            Column() {
                Greeting("Android")
                ButtonTest()
            }
        }
    }
}