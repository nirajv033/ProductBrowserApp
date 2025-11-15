plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    // Room step4
    alias(libs.plugins.ksp) apply false
}

buildscript {
    dependencies {
        classpath("com.squareup.sqldelight:gradle-plugin:1.5.5")
    }
}