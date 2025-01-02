allprojects {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
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