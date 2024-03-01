rootProject.name = settings.extra["name"] as String
pluginManagement {
    plugins {
        id("org.springframework.boot").version(settings.extra["spring_boot_plugin_version"] as String)
        id("io.spring.dependency-management").version(settings.extra["spring_dependency_management_plugin_version"] as String)
        id("io.freefair.aspectj.post-compile-weaving").version(settings.extra["aspectj_plugin_version"] as String)
        id("io.freefair.lombok").version(settings.extra["lombok_plugin_version"] as String)
        id("com.github.node-gradle.node").version(settings.extra["node_plugin_version"] as String)
    }
}