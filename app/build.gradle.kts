plugins {
    id("com.android.application")
}

android {
    namespace = "com.toolgits.xezenon"
    compileSdk = 36

    sourceSets {
        getByName("main") {
            kotlin.directories.add("../BoxHead")
        }
    }

    defaultConfig {
        applicationId = "com.toolgits.xezenon"
        minSdk = 23
        targetSdk = 36
        versionCode = 3
        versionName = "1.1.1"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.17.0")
}