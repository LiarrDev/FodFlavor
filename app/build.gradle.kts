plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.fod.flavor"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.fod.flavor"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    flavorDimensions += "flavor"
    productFlavors {
        create("appConvert") {
            applicationId = "com.csg.zb1"
        }
    }

    signingConfigs {
        create("config") {
            storeFile = file("signature/keystore.jks")
            storePassword = "fod123456"
            keyAlias = "fod"
            keyPassword = "fod123456"
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("config")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("config")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    "appConvertImplementation"(project(":AppConvert"))
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}