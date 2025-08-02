plugins {
  id("com.boshra.league.gradle.android.feature")
  id("com.boshra.league.gradle.android.library.compose")
}

android {
  namespace = "com.boshra.league.feature.posts"
}
dependencies {
  implementation(project(":domain:posts"))
  implementation(project(":data:league-posts"))
  implementation(project(":data:league-auth"))
  implementation(libs.lottie.compose)
}
