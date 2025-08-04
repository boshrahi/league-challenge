plugins {
  id("com.boshra.league.gradle.android.data")
}

android {
  namespace = "com.boshra.league.data.storage"
}

dependencies {
  api(libs.paging.runtime)
}
