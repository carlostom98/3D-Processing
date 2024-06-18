package com.alisys.androidar

import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.alisys.androidar.presenter.CustomizedThemes
import com.google.ar.core.Frame
import com.google.ar.core.TrackingFailureReason
import io.github.sceneview.Scene
import io.github.sceneview.ar.rememberARCameraNode
import io.github.sceneview.collision.HitResult
import io.github.sceneview.loaders.ModelLoader
import io.github.sceneview.math.Position
import io.github.sceneview.math.Rotation
import io.github.sceneview.node.CylinderNode
import io.github.sceneview.node.ModelNode
import io.github.sceneview.node.Node
import io.github.sceneview.rememberCameraManipulator
import io.github.sceneview.rememberCameraNode
import io.github.sceneview.rememberCollisionSystem
import io.github.sceneview.rememberEngine
import io.github.sceneview.rememberEnvironment
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

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

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
                                        assetFileLocation = "models/nissan_car.glb"
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

                        onFrame = { frameTimeNanos ->
                        }
                    )

                }
            }
        }
    }
}
