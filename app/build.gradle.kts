plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.kapt)
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "com.rdissi.bfortest"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.rdissi.bfortest"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    implementation(libs.androidx.constraintlayout.compose)

    implementation(libs.androidx.navigation)

    // Hilt dependencies
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.hilt.navigation.compose)
    kapt(libs.hilt.compiler)

    // AsyncImage
    implementation(libs.coilKt)
    implementation(libs.coilSvg)

    // Retrofit
    implementation(libs.retrofit2)
    implementation(libs.retrofit2.converter.gson)
    implementation(libs.retrofit2.okhttp)
    implementation(libs.retrofit2.logging.interceptor)
    implementation(libs.gson)

    //Paging
    implementation(libs.paging.compose)
    implementation(libs.paging.ktx)
    
    testImplementation(libs.junit)
    testImplementation(libs.jupiter)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //// Hilt testing dependency
    androidTestImplementation(libs.hilt.android.testing)
    // Make Hilt generate code in the androidTest folder
    kaptAndroidTest(libs.hilt.android.compiler)
    // Optional -- Mockito framework
    testImplementation(libs.mockito.core)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.mockito.inline)
    testImplementation(libs.mockito.nhaarman)
    testImplementation(libs.archCoreTestingVersionAndroidX)
    testImplementation(libs.coroutines.test)
    testImplementation(libs.turbine)

    testImplementation(libs.jupiter)
}