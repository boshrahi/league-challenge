plugins {
  id("com.boshra.league.gradle.android.data")
}

android {
  namespace = "com.boshra.league.data.auth"
}

dependencies {

  implementation(project(":data:api"))
  implementation(project(":data:storage"))
  implementation(project(":domain:repos"))
  api(libs.paging.runtime)
}
