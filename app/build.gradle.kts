plugins {
    id("com.android.application")
}

android {
    namespace = "com.toolgits.xezenon"
    compileSdk = 36

    sourceSets {
        getByName("main") {
            java.srcDirs(
                "src/main/java",
                "../../BoxHead"
            )
        }
    }

    defaultConfig {
        applicationId = "com.toolgits.xezenon"
        minSdk = 23
        targetSdk = 36
        versionCode = 1
        versionName = "0.1.0"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
}