
import org.gradle.kotlin.dsl.implementation

plugins {
    kotlin("jvm") version "2.1.0"
}

dependencyLocking {
    lockAllConfigurations()
}

tasks {
    register("resolveAndLockAll") {
        notCompatibleWithConfigurationCache("Filters configurations at execution time")
        doFirst {
            require(gradle.startParameter.isWriteDependencyLocks)
        }
        doLast {
            configurations.filter { it.isCanBeResolved}.forEach { it.resolve() }
        }
    }
}