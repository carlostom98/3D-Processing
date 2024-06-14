package com.alisys.androidar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.alisys.androidar.databinding.ActivityMainBinding
import com.alisys.androidar.presenter.CustomizedThemes
import com.google.android.filament.Box

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val darkMode = remember {
                mutableStateOf(false)
            }
            CustomizedThemes(darkMode.value) {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "Jetpack Configurado")
                }
            }
        }
    }

}