plugins {
  id("com.boshra.league.gradle.android.data")
}

android {
  namespace = "com.boshra.league.challenge.data.api"
}

dependencies {
  implementation(project(":data:network"))
}
