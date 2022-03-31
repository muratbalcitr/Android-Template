import Dependencies.Project.data

plugins {
    // Application Specific Plugins
    id(Plugins.androidApplication)
    kotlin(Plugins.android)
    kotlin(Plugins.kapt)
    kotlin(Plugins.androidExtensions)
    id(Plugins.daggerHiltPlugin)
}

android {
    compileSdkVersion(Configs.compileSdkVersion)
    buildToolsVersion = Configs.buildToolsVersion

    defaultConfig {
        configurations.all {
            resolutionStrategy {
                force("androidx.core:core-ktx:1.6.0")
                force("androidx.appcompat:appcompat:1.4.0")
            }
        }
        applicationId = Configs.applicationId
        minSdkVersion(Configs.minSdkVersion)
        targetSdkVersion(Configs.targetSdkVersion)

        versionCode = Configs.versionCode
        versionName = Configs.versionName
        multiDexEnabled = true
        testInstrumentationRunner = Configs.testInstrumentationRunner
    }

    signingConfigs {
        register(Flavors.BuildTypes.RELEASE) {
            storeFile = file(KeyStore.storeFilePath)
            storePassword = KeyStore.storePassword
            keyAlias = KeyStore.keyAlias
            keyPassword = KeyStore.keyPassword
        }
    }

    buildTypes {
        getByName(Flavors.BuildTypes.DEBUG) {
            isTestCoverageEnabled = true
            isMinifyEnabled = false
            isDebuggable = true
            applicationIdSuffix = ".${Flavors.BuildTypes.DEBUG}"
            // signingConfig = signingConfigs.getByName(Flavors.BuildTypes.RELEASE)
        }

        getByName(Flavors.BuildTypes.RELEASE) {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            // signingConfig = signingConfigs.getByName(Flavors.BuildTypes.RELEASE)
        }
    }

    flavorDimensions(Flavors.FlavorDimensions.ENVIRONMENT)
    productFlavors {

        // dev
        create(Flavors.ProductFlavors.DEV) {
            dimension = Flavors.FlavorDimensions.ENVIRONMENT
            applicationIdSuffix = ".${Flavors.ProductFlavors.DEV}"
            versionNameSuffix = "-${Flavors.ProductFlavors.DEV}"

            // BuildConfigField
            stringField(Field.SERVICE_URL to "https://api.themoviedb.org/3/")
            stringField(Field.SERVICE_PUBLIC_KEY to "")
            stringField(Field.SERVICE_CERTIFICATE_PATH to "")
        }

        // store
        create(Flavors.ProductFlavors.STORE) {
            dimension = Flavors.FlavorDimensions.ENVIRONMENT
            applicationIdSuffix = ""
            versionNameSuffix = ""

            // BuildConfigField
            stringField(Field.SERVICE_URL to "https://api.themoviedb.org/3/")
            stringField(Field.SERVICE_PUBLIC_KEY to "")
            stringField(Field.SERVICE_CERTIFICATE_PATH to "")
        }
    }

    sourceSets {
        getByName(Flavors.Default.MAIN) {
            java.srcDir("src/main/kotlin")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    buildFeatures {
        dataBinding = true
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    packagingOptions {
        exclude("META-INF/DEPENDENCIES")
        exclude("META-INF/LICENSE")
        exclude("META-INF/LICENSE.txt")
        exclude("META-INF/license.txt")
        exclude("META-INF/NOTICE")
        exclude("META-INF/NOTICE.txt")
        exclude("META-INF/notice.txt")
        exclude("META-INF/ASL2.0")
        exclude("META-INF/AL2.0")
        exclude("META-INF/LGPL2.1")
        exclude("META-INF/*.kotlin_module")
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))

    // Data Module
    implementation(data())

    // Kotlin
    implementation(Dependencies.Kotlin.kotlinStdLib)
    implementation(Dependencies.Kotlin.kotlinCoroutinesCore)
    implementation(Dependencies.Kotlin.kotlinCoroutinesAndroid)

    // Android
    implementation(Dependencies.Android.androidCore)
    implementation(Dependencies.Android.appCompat)
    implementation(Dependencies.Android.legacySupport)
    implementation(Dependencies.Android.multidex)
    implementation(Dependencies.Android.materialDesign)
    implementation(Dependencies.Android.fragment)
    implementation(Dependencies.Android.constraintLayout)
    implementation(Dependencies.Android.recyclerView)
    implementation(Dependencies.Android.recyclerViewSelection)
    implementation(Dependencies.Android.cardView)
    implementation(Dependencies.Android.palette)
    implementation(Dependencies.Android.workManger)

    // Coroutines
    implementation(Dependencies.Coroutines.coroutinesAandroid)
    implementation(Dependencies.Coroutines.coroutinesCore)
    implementation(Dependencies.Coroutines.coroutinesTest)
    implementation(Dependencies.Coroutines.kotlinCoroutinesAdapter)

    // Navigation
    implementation(Dependencies.Navigation.runTimeNavigation)
    implementation(Dependencies.Navigation.navigationFragment)
    implementation(Dependencies.Navigation.navigationUi)

    // LifeCycle
    implementation(Dependencies.LifeCycle.runTimeLiveCycle)
    implementation(Dependencies.LifeCycle.lifeCycleCompiler)
    implementation(Dependencies.LifeCycle.liveData)
    implementation(Dependencies.LifeCycle.viewModel)
    implementation(Dependencies.LifeCycle.viewModelState)

    // DI
    implementation(Dependencies.DI.hilt)
    implementation(Dependencies.DI.hiltWork)
    implementation(Dependencies.DI.hiltNavigation)
    // implementation(Dependencies.DI.hiltViewModel)
    kapt(Dependencies.DI.hiltCompiler)
    kapt(Dependencies.DI.hiltWorkManagerCompiler)
    annotationProcessor(Dependencies.DI.hiltWorkManagerCompiler)

    /*// ReactiveFunc
    implementation(Dependencies.ReactiveFunc.rxJava)
    implementation(Dependencies.ReactiveFunc.rxKotlin)
    implementation(Dependencies.ReactiveFunc.rxAndroid)*/

    // Timber
    implementation(Dependencies.Tools.timber)
    implementation(Dependencies.Tools.coil)
    // implementation(Dependencies.Tools.storyView)

    // Lottie (Animation)
    implementation(Dependencies.Tools.lottie)

    // Network
    implementation(Dependencies.Network.moshi)
    implementation(Dependencies.Network.moshiKotlin)
    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.Network.rxJavaAdapter)
    implementation(Dependencies.Network.okHttp)
    implementation(Dependencies.Network.loggingInterceptor)
    implementation(Dependencies.Network.conscrypt)

    // TOOLS

    implementation(Dependencies.Tools.roundedImageView)
    implementation(Dependencies.Tools.materialSearchBar)
    implementation(Dependencies.Tools.whynotimagecarousel)

    // Dialog
    implementation(Dependencies.Dialogs.dialogCore)

    // Testing
    testImplementation(Dependencies.Test.junit)
    testImplementation(Dependencies.Test.truthExt)
    testImplementation(Dependencies.Test.mockK)
    testImplementation(Dependencies.Test.coreTesting)
    androidTestImplementation(Dependencies.Test.androidJunit)
    androidTestImplementation(Dependencies.Test.espressoCore)
}

fun com.android.build.gradle.internal.dsl.ProductFlavor.stringField(entry: Pair<String, String>) {
    buildConfigField("String", entry.first, "\"${entry.second}\"")
}
