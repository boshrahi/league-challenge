plugins {
  id("com.boshra.league.gradle.android.data")
  id("kotlinx-serialization")
}

android {
  namespace = "com.boshra.league.data.network"

  defaultConfig {
    /*buildConfigField("String", "API_KEY",
      "\"${System.getenv("YOUR_API_KEY")}\"")*/
  }

  buildFeatures {
    buildConfig = true
  }
}

dependencies {

  implementation(libs.retrofit)
  implementation(libs.kotlinx.serialization.json)
  implementation(libs.kotlinx.serialization.json.converter)
}
