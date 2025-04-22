import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  `kotlin-dsl`
}

group = "com.boshra.league.build"

// for building convention module
java {
  sourceCompatibility = JavaVersion.VERSION_17
  targetCompatibility = JavaVersion.VERSION_17
}
tasks.withType<KotlinCompile>().configureEach {
  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_17.toString()
  }
}

dependencies {
  compileOnly(libs.android.gradlePlugin)
  compileOnly(libs.kotlin.gradlePlugin)
}

tasks {
  validatePlugins {
    enableStricterValidation = true
    failOnWarning = true
  }
}

gradlePlugin {
  plugins {
    register("androidApplicationCompose") {
      id = "com.boshra.league.gradle.android.application.compose"
      implementationClass = "com.boshra.league.gradle.plugins.ApplicationComposePlugin"
    }
    register("androidApplication") {
      id = "com.boshra.league.gradle.android.application"
      implementationClass = "com.boshra.league.gradle.plugins.ApplicationPlugin"
    }
    register("androidLibraryCompose") {
      id = "com.boshra.league.gradle.android.library.compose"
      implementationClass = "com.boshra.league.gradle.plugins.LibraryComposePlugin"
    }
    register("androidLibrary") {
      id = "com.boshra.league.gradle.android.library"
      implementationClass = "com.boshra.league.gradle.plugins.LibraryPlugin"
    }
    register("androidFeature") {
      id = "com.boshra.league.gradle.android.feature"
      implementationClass = "com.boshra.league.gradle.plugins.FeaturePlugin"
    }
    register("androidData") {
      id = "com.boshra.league.gradle.android.data"
      implementationClass = "com.boshra.league.gradle.plugins.DataPlugin"
    }
    register("androidDomain") {
      id = "com.boshra.league.gradle.android.domain"
      implementationClass = "com.boshra.league.gradle.plugins.DomainPlugin"
    }
    register("androidHilt") {
      id = "com.boshra.league.gradle.android.hilt"
      implementationClass = "com.boshra.league.gradle.plugins.HiltPlugin"
    }
    register("jvmLibrary") {
      id = "com.boshra.league.gradle.jvm.library"
      implementationClass = "com.boshra.league.gradle.plugins.LibraryJvmPlugin"
    }
  }
}
