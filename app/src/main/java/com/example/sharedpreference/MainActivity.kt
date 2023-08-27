package com.example.sharedpreference

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharedpreference.ui.theme.SharedPreferenceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SharedPreferenceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    SharedPreferenceFunction(context = LocalContext.current)
                }
            }
        }
    }


    @Composable
    fun SharedPreferenceFunction(context: Context) {
        val userName = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }

        val sharedPreferences = this@MainActivity.getPreferences(Context.MODE_PRIVATE)
        val prefMgrEditor = sharedPreferences.edit()
        prefMgrEditor.putBoolean("splash_shown", true).apply()


        val userNameStr = sharedPreferences.getString("name", "")
        val passwordStr = sharedPreferences.getString("age", "")

        userName.value = userNameStr!!
        password.value = passwordStr!!


        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                CommonTextField(label = "Username", value = userName.value, onChanged = {userName.value = it})

            }

            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
            ) {
                CommonTextField(label = "Password", value = password.value, onChanged = {password.value = it})

            }

            Button(
                onClick = { },
                shape = RoundedCornerShape(40.dp),
                colors = ButtonDefaults.buttonColors(Color.Blue),
                border = BorderStroke(width = 1.dp, color = Color.Blue),
                modifier = Modifier
                    .padding(top = 40.dp, start = 90.dp, end = 90.dp, bottom = 0.dp)
                    .fillMaxWidth()
                    .height(55.dp)
            ) {
                Text(
                    text = "LOGIN",
                    modifier = Modifier.padding(6.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.White,
                )
            }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CommonTextField(
        label: String,
        value: String,
        onChanged: (String) -> Unit
    ) {
        TextField(
            value = value,
            textStyle = TextStyle(fontSize = 14.sp),
            onValueChange = { onChanged(it) },
            placeholder = {
                Text(
                    text = label,
                    fontSize = 14.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(start = 0.dp)
                )
            },
            modifier = Modifier
                .fillMaxWidth(),
//                .height(50.dp),
            singleLine = true
        )
    }
}