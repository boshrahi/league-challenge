plugins {
  id("com.boshra.league.gradle.android.data")
}

android {
  namespace = "com.boshra.league.data.auth"
}

dependencies {

  implementation(project(":data:api"))
  api(libs.paging.runtime)
}
