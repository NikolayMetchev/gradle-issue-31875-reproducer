pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("com.pablisco.gradle.auto.include") version "1.3"
}

rootProject.name = "reproducer-project"
