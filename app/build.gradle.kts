plugins {
  id("com.boshra.league.gradle.android.application")
  alias(libs.plugins.kotlinAndroid)
}

android {
  namespace = "com.boshra.league"

  defaultConfig {
    applicationId = "com.boshra.league"
    versionCode = 1
    versionName = "1.0"
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

dependencies {

  implementation(project(":feature:posts"))
  implementation(libs.material3)
  implementation(libs.androidx.navigation.compose)
}
