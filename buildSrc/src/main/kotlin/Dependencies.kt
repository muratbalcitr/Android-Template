import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {

    object Kotlin {
        const val kotlinStdLib =
            "org.jetbrains.kotlin:kotlin-stdlib:${Versions.Kotlin.kotlinStdLib}"
        const val kotlinCoroutinesCore =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.kotlinCoroutinesCore}"
        const val kotlinCoroutinesAndroid =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.kotlinCoroutinesCore}"


        object Test {
            const val common =
                "org.jetbrains.kotlin:kotlin-test-common:${Versions.Kotlin.kotlinVersion}"
            const val annotations =
                "org.jetbrains.kotlin:kotlin-test-annotations-common:${Versions.Kotlin.kotlinVersion}"
            const val junit =
                "org.jetbrains.kotlin:kotlin-test-junit:${Versions.Kotlin.kotlinVersion}"
        }
    }

    object Android {
        const val androidCore =
            "androidx.core:core-ktx:${Versions.Android.androidCore}"
        const val appCompat =
            "androidx.appcompat:appcompat:${Versions.Android.appCompat}"
        const val legacySupport =
            "androidx.legacy:legacy-support-v4:${Versions.Android.legacySupport}"
        const val multidex =
            "androidx.multidex:multidex:${Versions.Android.multiDex}"
        const val materialDesign =
            "com.google.android.material:material:${Versions.Android.materialDesign}"
        const val fragment =
            "androidx.fragment:fragment-ktx:${Versions.Android.fragmentVersion}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.Android.constraintLayout}"
        const val recyclerView =
            "androidx.recyclerview:recyclerview:${Versions.Android.recyclerView}"
        const val recyclerViewSelection =
            "androidx.recyclerview:recyclerview:${Versions.Android.recyclerViewSelection}"
        const val cardView =
            "androidx.cardview:cardview:${Versions.Android.cardView}"
        const val palette =
            "androidx.palette:palette-ktx:${Versions.Android.palette}"
        const val workManger =
            "androidx.work:work-runtime-ktx:${Versions.Android.workManager}"
    }

    object Coroutines {

        // Coroutines
        const val coroutinesAandroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.2"
        const val kotlinCoroutinesAdapter =
            "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"
        const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.0"
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0"
    }

    object Navigation {
        const val runTimeNavigation =
            "androidx.navigation:navigation-runtime-ktx:${Versions.Navigation.runTimeNavigation}"
        const val navigationFragment =
            "androidx.navigation:navigation-fragment-ktx:${Versions.Navigation.navigationFragment}"
        const val navigationUi =
            "androidx.navigation:navigation-ui-ktx:${Versions.Navigation.navigationUI}"
    }

    object LifeCycle {
        const val runTimeLiveCycle =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.LifeCycle.runTimeLifeCycle}"
        const val lifeCycleCompiler =
            "androidx.lifecycle:lifecycle-compiler:${Versions.LifeCycle.viewModelState}"
        const val liveData =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LifeCycle.liveData}"
        const val viewModel =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LifeCycle.viewModel}"
        const val viewModelState =
            "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.LifeCycle.viewModelState}"
    }

    object DI {
        const val hilt =
            "com.google.dagger:hilt-android:${Versions.DI.hilt}"
        const val hiltWork =
            "androidx.hilt:hilt-work:${Versions.DI.hiltWork}"
        const val hiltCompiler =
            "com.google.dagger:hilt-android-compiler:${Versions.DI.hilt}"
        const val hiltNavigation =
            "androidx.hilt:hilt-navigation-fragment:${Versions.DI.hiltNavigation}"
        const val hiltViewModel =
            "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.DI.hiltViewModel}"
        const val hiltWorkManagerCompiler =
            "androidx.hilt:hilt-compiler:${Versions.DI.hiltCompiler}"
    }

    object ReactiveFunc {
        const val rxJava =
            "io.reactivex.rxjava3:rxjava:${Versions.ReactiveFunc.rxJava}"
        const val rxKotlin =
            "io.reactivex.rxjava3:rxkotlin:${Versions.ReactiveFunc.rxKotlin}"
        const val rxAndroid =
            "io.reactivex.rxjava3:rxandroid:${Versions.ReactiveFunc.rxAndroid}"
    }

    object Network {
        const val moshi =
            "com.squareup.retrofit2:converter-moshi:${Versions.Network.moshi}"
        const val moshiKotlin =
            "com.squareup.moshi:moshi-kotlin:${Versions.Network.moshiKotlin}"
        const val retrofit =
            "com.squareup.retrofit2:retrofit:${Versions.Network.retrofit}"
        const val rxJavaAdapter =
            "com.squareup.retrofit2:adapter-rxjava3:${Versions.Network.rxJava3Adapter}"
        const val okHttp =
            "com.squareup.okhttp3:okhttp:${Versions.Network.okHttp}"
        const val loggingInterceptor =
            "com.squareup.okhttp3:logging-interceptor:${Versions.Network.loggingInterceptor}"
        const val conscrypt =
            "org.conscrypt:conscrypt-android:${Versions.Network.conscrypt}"
    }

    object Tools {
        const val materialSearchBar =
            "com.github.mancj:MaterialSearchBar:${Versions.Tools.materialSearchBar}"

        const val roundedImageView =
            "com.makeramen:roundedimageview:${Versions.Tools.roundedImageView}"
        const val whynotimagecarousel =
            "org.imaginativeworld.whynotimagecarousel:whynotimagecarousel:${Versions.Tools.whynotimagecarousel}"

        const val coil =
            "io.coil-kt:coil:${Versions.Tools.coil}"
        const val timber =
            "com.jakewharton.timber:timber:${Versions.Tools.timber}"
        const val lottie =
            "com.airbnb.android:lottie:${Versions.Tools.lottie}"
    }

    object Dialogs {
        const val dialogCore = "com.afollestad.material-dialogs:core:${Versions.Dialogs.core}"
    }

    object Project {
        fun DependencyHandler.app() = project(mapOf("path" to ":app"))
        fun DependencyHandler.data() = project(mapOf("path" to ":data"))
    }

    object Test {
        const val junit =
            "junit:junit:${Versions.Test.junit}"
        const val androidJunit =
            "androidx.test.ext:junit:${Versions.Test.androidJunit}"
        const val espressoCore =
            "androidx.test.espresso:espresso-core:${Versions.Test.espressoCore}"
        const val truthExt =
            "androidx.test.ext:truth:${Versions.Test.truthExtVersion}"
        const val mockK =
            "io.mockk:mockk:${Versions.Test.mockKVersion}"
        const val coreTesting =
            "androidx.arch.core:core-testing:${Versions.Test.coreTestingVersion}"
    }
}
