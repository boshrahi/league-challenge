package com.boshra.league.gradle.plugins

import com.android.build.api.dsl.ApplicationExtension
import com.boshra.league.gradle.Versions
import com.boshra.league.gradle.configureKotlinAndroid
import com.boshra.league.gradle.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class ApplicationPlugin : Plugin<Project> {
  override fun apply(target: Project) {
    with(target) {
      with(pluginManager) {
        apply("com.android.application")
        apply("org.jetbrains.kotlin.android")
        apply("org.jetbrains.kotlin.plugin.compose")

        apply("com.google.gms.google-services")
        apply("com.google.firebase.crashlytics")

        apply("com.boshra.league.gradle.android.application.compose")
        apply("com.boshra.league.gradle.android.hilt")
      }

      extensions.configure<ApplicationExtension> {
        configureKotlinAndroid()

        defaultConfig {
          targetSdk = Versions.TARGET_SDK

          signingConfigs {
            create("release") {
              keyAlias = System.getenv("KEY_ALIAS")
              keyPassword = System.getenv("KEY_PASSWORD")
              storeFile = file("${System.getenv("KEY_STORE_FILE_PATH")}")
              storePassword = System.getenv("KEY_STORE_PASSWORD")
            }
          }

          buildTypes {
            getByName("release") {
              isMinifyEnabled = true
              proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
              )
              signingConfig = signingConfigs.getByName("release")
            }
          }
        }
      }

      dependencies {
        add("implementation", libs.findLibrary("firebase-crashlytics").get())
        add("implementation", libs.findLibrary("firebase-analytics").get())
        add("implementation", platform(libs.findLibrary("firebase-bom").get()))
        add("testImplementation", libs.findLibrary("junit").get())
        add("androidTestImplementation", libs.findLibrary("androidx-test-ext-junit").get())
      }
    }
  }
}
