plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.jetbrains.kotlin.parcelize)
    alias(libs.plugins.kapt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.ktlint)
}

android {
    namespace = "com.example.nba"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.nba"
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
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }

    buildFeatures { // Enables Jetpack Compose for this module
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.8"
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.material)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.constraintlayout)

    implementation(libs.androidx.compose.graphics)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.material.iconsExtended)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.ui.util)
    debugApi(libs.androidx.compose.ui.tooling)

    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // navigation
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.navigation.ui.ktx)

    // paging
    implementation(libs.androidx.paging.runtime.ktx)
    implementation(libs.androidx.paging.compose)

    // Coroutines/kotlin
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlinx.collections.immutable)

    // Networking
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit)
    implementation(libs.sandwich)
    implementation(libs.sandwich.retrofit)
    implementation(libs.sandwich.retrofit.serialization)
    implementation(libs.converter.gson)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
    implementation(libs.javax.annotation.api)

    // dependency injection libraries
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)

    // other
    implementation(libs.timber)

    implementation(libs.glide.compose)
}

ktlint {
    debug.set(false)
    verbose.set(true)
    android.set(false)
    coloredOutput.set(true)
    outputToConsole.set(true)
    ignoreFailures.set(false)
    enableExperimentalRules.set(true)
    filter {
        exclude("**/generated/**")
        include("**/kotlin/**")
    }
}
