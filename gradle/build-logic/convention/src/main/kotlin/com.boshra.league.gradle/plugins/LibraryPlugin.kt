package com.boshra.league.gradle.plugins

import com.android.build.gradle.LibraryExtension
import com.boshra.league.gradle.Versions
import com.boshra.league.gradle.configureKotlinAndroid
import com.boshra.league.gradle.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class LibraryPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("com.android.library")
        apply("org.jetbrains.kotlin.android")
      }

      extensions.configure<LibraryExtension> {
        configureKotlinAndroid()
        defaultConfig.targetSdk = Versions.TARGET_SDK

        buildTypes {
          getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
              getDefaultProguardFile("proguard-android-optimize.txt"),
              "proguard-rules.pro",
            )
            consumerProguardFiles("consumer-rules.pro")
          }
        }
      }

      dependencies {
        add("testImplementation", kotlin("test"))
        add("testImplementation", libs.findLibrary("junit").get())
        add("androidTestImplementation", libs.findLibrary("androidx-test-ext-junit").get())
        add("androidTestImplementation", libs.findLibrary("espresso-core").get())
      }
    }
  }
}
