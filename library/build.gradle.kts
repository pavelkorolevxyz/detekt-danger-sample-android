plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("convention.detekt")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        targetSdk = 32
    }
}
