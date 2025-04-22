plugins {
  id("com.boshra.league.gradle.android.domain")
}

android {
  namespace = "com.boshra.league.domain.domain.posts"
}

dependencies {
  implementation(project(":data:league-auth"))
  implementation(project(":data:league-posts"))
  api(libs.paging.runtime)
}
