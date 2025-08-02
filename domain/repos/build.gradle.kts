plugins {
  id("com.boshra.league.gradle.android.domain")
}

android {
  namespace = "com.boshra.league.domain.domain.repos"
}

dependencies {
  api(libs.paging.runtime)
}
