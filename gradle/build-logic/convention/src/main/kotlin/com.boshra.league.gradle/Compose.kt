package com.boshra.league.gradle

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidCompose(
  commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
  commonExtension.apply {
    buildFeatures {
      compose = true
    }

    // No need of this because kotlin 2.1.0
/*    composeOptions {
      kotlinCompilerExtensionVersion = libs.findVersion("compose-compiler").get().toString()
    }*/

    dependencies {
      val bom = libs.findLibrary("compose-bom").get()
      add("implementation", platform(bom))
      add("androidTestImplementation", platform(bom))
      add("implementation", libs.findLibrary("ui-tooling-preview").get())
      add("debugImplementation", libs.findLibrary("ui-tooling").get())
      add("implementation", libs.findLibrary("compose-material").get())
    }
  }
}
