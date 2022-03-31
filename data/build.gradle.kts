plugins {
    id(Plugins.androidLibrary)
    kotlin(Plugins.android)
    kotlin(Plugins.kapt)
    kotlin(Plugins.androidExtensions)
    id(Plugins.daggerHiltPlugin)
}

android {
    compileSdkVersion(Configs.compileSdkVersion)
    buildToolsVersion = Configs.buildToolsVersion

    defaultConfig {
        minSdkVersion(Configs.minSdkVersion)
        targetSdkVersion(Configs.targetSdkVersion)

        versionCode = Configs.versionCode
        versionName = Configs.versionName
        testInstrumentationRunner = Configs.testInstrumentationRunner
    }

    sourceSets {
        getByName(Flavors.Default.MAIN) {
            java.srcDir("src/main/kotlin")
        }
    }

    buildTypes {
        getByName(Flavors.BuildTypes.RELEASE) {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))

    // Kotlin
    implementation(Dependencies.Kotlin.kotlinStdLib)

    // Android
    implementation(Dependencies.Android.androidCore)

    // Network
    api(Dependencies.Network.moshi)
    api(Dependencies.Network.moshiKotlin)
    api(Dependencies.Network.retrofit)
    api(Dependencies.Network.rxJavaAdapter)
    api(Dependencies.Network.okHttp)
    api(Dependencies.Network.loggingInterceptor)
    api(Dependencies.Network.conscrypt)

    // DI
    api(Dependencies.DI.hilt)
    kapt(Dependencies.DI.hiltCompiler)
/*
    // ReactiveFunc
    api(Dependencies.ReactiveFunc.rxJava)*/

    // Timber
    api(Dependencies.Tools.timber)

    // Testing
    testImplementation(Dependencies.Test.junit)
    androidTestImplementation(Dependencies.Test.androidJunit)
    androidTestImplementation(Dependencies.Test.espressoCore)
}
