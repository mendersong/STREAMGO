plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.plugin.serialization")
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
    packaging { jniLibs { useLegacyPackaging = true } }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":capture:engine"))
    implementation(project(":encoder:engine"))
    implementation(project(":streaming:engine"))
    implementation(project(":security:manager"))
    
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.compose.ui:ui:1.6.1")
    implementation("androidx.compose.material3:material3:1.1.2")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")
    implementation("com.google.dagger:hilt-android:2.48")
    implementation("com.github.pedroSG94.rtmp-rtsp-stream-client-java:rtplibrary:2.4.8")
    implementation("com.jiangdg:libuvccamera:1.0.7")
    implementation("androidx.security:security-crypto:1.1.0-alpha06")
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.datastore:datastore-preferences:1.0.0")
}
