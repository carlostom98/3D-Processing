package com.alisys.androidar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.alisys.androidar.presenter.CustomizedThemes

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

//        setContent {
//            val darkMode = remember {
//                mutableStateOf(false)
//            }
//            CustomizedThemes(darkMode.value) {
//                Box {
//                    Text(text = "Jetpack Configurado")
//                }
//            }
//        }
    }

}