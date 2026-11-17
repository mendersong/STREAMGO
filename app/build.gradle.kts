plugins {
    id("com.android.application") version "8.2.2"
    id("org.jetbrains.kotlin.android") version "1.9.22"
    id("kotlin-kapt") version "1.9.22"
    id("dagger.hilt.android.plugin") version "2.48"
}

android {
    namespace = "com.streamgo.app"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.streamgo.app"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0.0"
    }
    buildFeatures { compose = true }
    composeOptions { kotlinCompilerExtensionVersion = "1.5.8" }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions { jvmTarget = "17" }
    packaging { jniLibs { useLegacyPackaging = true } }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.compose.ui:ui:1.6.1")
    implementation("androidx.compose.material3:material3:1.1.2")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-compiler:2.48")
    implementation("androidx.security:security-crypto:1.1.0-alpha06")
    implementation("com.github.pedroSG94.rtmp-rtsp-stream-client-java:rtplibrary:2.4.8")
    // Lib UVC temporariamente comentada para garantir o build do APK.
    // implementation("com.jiangdg:libuvccamera:1.0.7")
}
