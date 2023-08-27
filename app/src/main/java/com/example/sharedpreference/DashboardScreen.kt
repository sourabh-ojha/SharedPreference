package com.example.sharedpreference

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharedpreference.ui.theme.SharedPreferenceTheme
import com.example.sharedpreference.util.PrefManager

class DashboardScreen : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SharedPreferenceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    DashboardFunction()
                }
            }
        }
    }


   @Composable
   fun DashboardFunction(){
       Column(Modifier.fillMaxSize(),
       horizontalAlignment = Alignment.CenterHorizontally,
       verticalArrangement = Arrangement.Center) {

           Button(
               onClick = {
                   PrefManager(this@DashboardScreen).save("isLoggedIn", false)
                   startActivity(Intent(this@DashboardScreen, MainActivity::class.java))
                   finish()
               },
               shape = RoundedCornerShape(40.dp),
               colors = ButtonDefaults.buttonColors(Color.Blue),
               border = BorderStroke(width = 1.dp, color = Color.Blue),
               modifier = Modifier
                   .padding(top = 40.dp, start = 90.dp, end = 90.dp, bottom = 0.dp)
                   .fillMaxWidth()
                   .height(55.dp)
           ) {
               Text(
                   text = "Log Out",
                   modifier = Modifier.padding(6.dp),
                   fontWeight = FontWeight.Bold,
                   fontSize = 14.sp,
                   color = Color.White,
               )
           }

       }
   }
}