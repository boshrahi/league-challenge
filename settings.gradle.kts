pluginManagement {
  includeBuild("gradle/build-logic")
  repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
  }
}
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
  }
}
rootProject.name = "league-challenge"
include(":app")
include(":data")
include(":data:model")
include(":data:network")
include(":domain")
include(":data:api")
include(":data:league-auth")
include(":data:league-posts")
include(":feature:posts")
include(":domain:posts")
include(":core")
