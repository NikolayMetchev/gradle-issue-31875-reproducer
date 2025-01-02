import org.gradle.kotlin.dsl.implementation

plugins {
    kotlin("jvm") version "2.1.0"
}

configurations {
    compileClasspath {
        resolutionStrategy.activateDependencyLocking()
    }
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
            configurations
                .filter {
                    // Add any custom filtering on the configurations to be resolved
                    (
                            it.name !in
                                    listOf(
                                        "projectHealthClasspath",
                                        "resolvedDepsClasspath",
                                        "testDependencySources",
                                        "dependencySources",
                                        "kotlinNativeBundleConfiguration",
                                        "combinedGraphClasspath",
                                    )
                            ) &&
                            it.isCanBeResolved
                }.forEach { it.resolve() }
        }
    }
}