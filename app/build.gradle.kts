plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.app.zerobrokagepackerandmover"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.app.zerobrokagepackerandmover"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.activity)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")

// Converter (e.g., for JSON using Gson)
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

// OkHttp (for networking)
    implementation ("com.squareup.okhttp3:okhttp:4.12.0")

// Logging Interceptor (for debugging network calls)
    implementation ("com.squareup.okhttp3:logging-interceptor:4.12.0")

    //Country Picker
    implementation("com.hbb20:ccp:2.7.3")
    implementation("io.github.chaosleung:pinview:1.4.4")

    //Otp View
    implementation("com.github.aabhasr1:OtpView:v1.1.2-ktx")

    //Map
    implementation("com.google.android.material:material:1.11.0")

    //OlaMap SDK
    implementation(files("libs/olamaps.aar"))

//Maplibre
    implementation("org.maplibre.gl:android-sdk:10.0.2")
    implementation("org.maplibre.gl:android-plugin-annotation-v9:1.0.0")
    implementation("org.maplibre.gl:android-plugin-markerview-v9:1.0.0")

    // Retrofit for networking
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

// Converter for JSON (using Gson)
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")




}