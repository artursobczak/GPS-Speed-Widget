plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "pl.artur.gpsspeedwidget"
    compileSdk = 35

    defaultConfig {
        applicationId = "pl.artur.gpsspeedwidget"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }
}

kotlin { jvmToolchain(17) }
