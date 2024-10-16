plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = libs.plugins.main.namespace.get().toString()
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        compose = true
    }

    kotlinOptions {
        jvmTarget = libs.versions.jvmTarget.get()
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlinCompiler.get()
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Presentation Dependencies
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material)

    // DI
    implementation(libs.hilt.core)
    ksp(libs.hilt.compiler)

    // Navigation
    implementation(libs.compose.hilt.navigation)

    // Coil
    implementation(libs.coil)
    implementation(libs.coil.compose)

    // Datastore
    api(libs.androidx.datastore.preferences)

    // Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
}