// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
  alias(libs.plugins.androidApplication) apply false
  alias(libs.plugins.kotlinAndroid) apply false
  alias(libs.plugins.org.jetbrains.kotlin.jvm) apply false
  alias(libs.plugins.androidLibrary) apply false
  alias(libs.plugins.kotlinx.serialization) apply false
  alias(libs.plugins.hilt.android) apply false
  alias(libs.plugins.google.service) apply false
  alias(libs.plugins.firebase) apply false
  alias(libs.plugins.compose.plugin) apply false
  alias(libs.plugins.spotless)
}
spotless {
  kotlin {
    target("**/*.kt", "**/*.kts")
    targetExclude("$buildDir/**/*.kt", "bin/**/*.kt", "buildSrc/**/*.kt")
    // version, editorConfigPath, editorConfigOverride and customRuleSets are all optional
    ktlint().setEditorConfigPath("$rootDir/.editorconfig") // sample unusual placement
  }
}
val installGitHook by tasks.registering(Copy::class) {
  from(file("${rootProject.rootDir}/.scripts/pre-commit"))
  into(file("${rootProject.rootDir}/.git/hooks"))
  fileMode = 0b111101101
}
project(":app").afterEvaluate {
  tasks.named("preBuild").configure {
    dependsOn(":installGitHook")
  }
}
// true // Needed to make the Suppress annotation work for the plugins block
