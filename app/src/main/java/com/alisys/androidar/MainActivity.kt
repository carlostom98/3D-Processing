package com.alisys.androidar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.work.WorkManager
import com.alisys.androidar.presenter.CustomizedThemes
import com.google.ar.core.ArCoreApk
import com.google.ar.core.Config
import com.google.ar.core.Session
import dagger.hilt.android.AndroidEntryPoint

private const val kModelFile = "models/gumball.glb"
private const val kModelFile2 = "models/archivo.glb"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var workManager: WorkManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        workManager = WorkManager.getInstance(applicationContext)

        setContent {
//  val viewModel = hiltViewModel<TestDaggerViewModel>()
            val darkMode = remember {
                mutableStateOf(false)
            }
            var isAvailable by remember {
                mutableStateOf(true)
            }

            maybeEnableArButton() { available ->
                isAvailable = available
            }

            CustomizedThemes(darkMode.value) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        if (isAvailable) {
                            Text(text = "Is Available")
                        }else {
                            Text(text = "Not Available")
                        }
                    }

                }
            }
        }
    }

    private fun maybeEnableArButton(callback: (isAvailable: Boolean) -> Unit) {


    }

//    override fun onNewIntent(intent: Intent) {
//        super.onNewIntent(intent)
//        val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            intent.getParcelableExtra(Intent.EXTRA_STREAM, Uri::class.java)
//        } else {
//            intent.getParcelableExtra(Intent.EXTRA_STREAM)
//        } ?: return
//
//        viewModel.updateUncompressUri(uri)
//
//        val request = OneTimeWorkRequestBuilder<PhotoCompressionWorker>()
//            .setInputData(
//                workDataOf(
//                    PhotoCompressionWorker.KEY_CONTENT_URI to uri.toString(),
//                    PhotoCompressionWorker.KEY_COMPRESSION_THRESHOLD to 1024 * 20L
//                )
//            ).build()
//        viewModel.updateWorkId(request.id)
//        workManager.enqueue(request)
//    }
}
