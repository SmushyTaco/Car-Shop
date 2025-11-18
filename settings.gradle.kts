val name = providers.gradleProperty("name")
rootProject.name = name.get()
pluginManagement {
    val foojayResolverVersion = providers.gradleProperty("foojay_resolver_version")
    plugins {
        id("org.gradle.toolchains.foojay-resolver-convention").version(foojayResolverVersion)
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention")
}