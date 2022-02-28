object Classpaths {
    const val gradleClasspath =
        "com.android.tools.build:gradle:${Versions.Gradle.gradleVersion}"
    const val kotlinGradleClasspath =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Kotlin.kotlinVersion}"
    const val gradleVersionPlugin =
        "com.github.ben-manes:gradle-versions-plugin:${Versions.Gradle.gradleVersionPluginVersion}"
    const val hiltGradleClasspath =
        "com.google.dagger:hilt-android-gradle-plugin:${Versions.Gradle.hiltVersion}"
}
