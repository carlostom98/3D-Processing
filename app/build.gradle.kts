plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.dagger)
}

android {
    namespace = "com.alisys.androidar"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.alisys.androidar"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // SceneView
    implementation(libs.sceneview)
    implementation(libs.sceneview.ar)

    // Compose
    implementation(libs.compose)
    implementation(libs.compose.rx)
    implementation(libs.compose.ui)
    implementation(libs.compose.tooling)
    implementation(libs.compose.material3)
    implementation(libs.compose.navigation)
    implementation(libs.compose.livedata)

    // Workmanager
    implementation(libs.work.manager)

    // Coil
    implementation(libs.coil)

    // Dagger Hilt
    implementation(libs.dagger.hilt)
    implementation(libs.hilt.viewmodel)
    implementation(libs.hilt.navigation)
    kapt(libs.kapt.hilt)
    kapt(libs.kapt.dagger)

}