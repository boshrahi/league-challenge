plugins {
  id("com.boshra.league.gradle.android.data")
}

android {
  namespace = "com.boshr.league.data.league.posts"
}

dependencies {

  implementation(project(":data:api"))
  implementation(project(":domain:repos"))
  api(libs.paging.runtime)
}
