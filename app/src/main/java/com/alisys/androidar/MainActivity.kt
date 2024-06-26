package com.alisys.androidar

import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.work.WorkManager
import com.alisys.androidar.presenter.CustomizedThemes
import com.alisys.androidar.viewModels.TestDaggerViewModel
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
private const val kModelFile2 = "models/archivo.glb"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var workManager: WorkManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        workManager = WorkManager.getInstance(applicationContext)

        setContent {
  val viewModel = hiltViewModel<TestDaggerViewModel>()
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
                        isOpaque = true,
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
                                        assetFileLocation = kModelFile2
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
