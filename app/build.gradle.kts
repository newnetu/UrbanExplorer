plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.test4"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.test4"
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
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.play.services.mlkit.text.recognition.common)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation ("com.amplifyframework:core:2.18.0")
    implementation("com.amplifyframework:aws-api:2.16.1")
    implementation("com.amplifyframework:aws-datastore:2.16.1")
    implementation("com.amplifyframework:aws-storage-s3:2.16.1")
    implementation("com.amplifyframework:aws-auth-cognito:2.18.0")
    implementation ("com.github.bumptech.glide:glide:4.14.2")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.14.2")
    implementation ("com.amazonaws:aws-android-sdk-s3:2.49.0")
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:1.1.5")


}