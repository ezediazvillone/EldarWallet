// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val kotlinVersion = "1.9.0"
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
}
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false

    val hiltVersion = "2.48"
    id("com.google.dagger.hilt.android") version hiltVersion apply false

    val navVersion = "2.7.7"
    id("androidx.navigation.safeargs") version navVersion apply false
}