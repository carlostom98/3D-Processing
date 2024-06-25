package com.alisys.androidar

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import coil.compose.AsyncImage
import com.alisys.androidar.presenter.CustomizedThemes
import com.alisys.androidar.viewModels.PhotoViewModel
import com.alisys.androidar.workmanager.PhotoCompressionWorker
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import io.github.sceneview.Scene
import io.github.sceneview.collision.HitResult
import io.github.sceneview.math.Position
import io.github.sceneview.node.ModelNode
import io.github.sceneview.rememberCameraManipulator
import io.github.sceneview.rememberCameraNode
import io.github.sceneview.rememberCollisionSystem
import io.github.sceneview.rememberEngine
import io.github.sceneview.rememberEnvironmentLoader
import io.github.sceneview.rememberMainLightNode
import io.github.sceneview.rememberMaterialLoader
import io.github.sceneview.rememberModelLoader
import io.github.sceneview.rememberNodes
import io.github.sceneview.rememberOnGestureListener
import io.github.sceneview.rememberRenderer
import io.github.sceneview.rememberScene
import io.github.sceneview.rememberView

private const val kModelFile = "models/gumball.glb"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var workManager: WorkManager
    private val viewModel by viewModels<PhotoViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        workManager = WorkManager.getInstance(applicationContext)

        setContent {
            val darkMode = remember {
                mutableStateOf(false)
            }
            val engine = rememberEngine()
            val view = rememberView(engine)
            val render = rememberRenderer(engine = engine)
            val scene = rememberScene(engine = engine)
            val modelLoader = rememberModelLoader(engine = engine)
            val materialLoader = rememberMaterialLoader(engine = engine)
            val environmentLoader = rememberEnvironmentLoader(engine = engine)

            val collisionSystem = rememberCollisionSystem(view = view)

            CustomizedThemes(darkMode.value) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    val workResult = viewModel.workId?.let { id ->
//                        workManager.getWorkInfoByIdLiveData(id).observeAsState().value
//                    }
//
//                    LaunchedEffect(key1 = workResult?.outputData) {
//                        if (workResult?.outputData != null) {
//                            val filePath = workResult.outputData.getString(
//                                PhotoCompressionWorker.KEY_RESULT_PATH
//                            )
//
//                            filePath?.let {
//                                val bitmap = BitmapFactory.decodeFile(it)
//                                viewModel.updateCompressBitmap(bitmap)
//                            }
//                        }
//                    }
//
//                    Column(
//                        modifier = Modifier.fillMaxSize(),
//                        verticalArrangement = Arrangement.Center,
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        viewModel.uncompressUri?.let {
//                            Text(text = "Uncompressed Photo")
//                            AsyncImage(model = it, contentDescription = null)
//                        }
//                        Spacer(modifier = Modifier.height(40.dp))
//                        viewModel.compressedBitmap?.let {
//                            Text(text = "Compressed Photo")
//                            Image(bitmap = it.asImageBitmap(), contentDescription = null)
//                        }
//                    }

                    Scene(
                        modifier = Modifier.fillMaxSize(),
                        engine = engine,
                        view = view,
                        renderer = render,
                        scene = scene,
                        modelLoader = modelLoader,
                        environmentLoader = environmentLoader,
                        collisionSystem = collisionSystem,
                        isOpaque = false,
                        mainLightNode = rememberMainLightNode(engine = engine) {
                            intensity = 100_000.0f
                        },
                        cameraNode = rememberCameraNode(engine) {
                            // Position the camera 4 units away from the object
                            position = Position(z = 4.0f)
                        },
                        cameraManipulator = rememberCameraManipulator(),
                        childNodes = rememberNodes {
                            // Add a glTF model
                            add(
                                ModelNode(
                                    // Load it from a binary .glb in the asset files
                                    modelInstance = modelLoader.createModelInstance(
                                        assetFileLocation = kModelFile
                                    ),
                                    scaleToUnits = 1.0f
                                )
                            )
                            // Add a Cylinder geometry

                            // ...See all available nodes in the nodes packagage
                        },
                        onGestureListener = rememberOnGestureListener(
                            onDoubleTapEvent = { event, tapedNode ->
                                // Scale up the tap node (if any) on double tap
                                tapedNode?.let { it.scale *= 2.0f }
                            }),

                        onTouchEvent = { event: MotionEvent, hitResult: HitResult? ->
                            hitResult?.let { println("World tapped : ${it.worldPosition}") }
                            // The touch event is not consumed
                            false
                        },


                    )

                }
            }
        }
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
