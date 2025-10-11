val name = providers.gradleProperty("name")
rootProject.name = name.get()
pluginManagement {
    val springBootPluginVersion = providers.gradleProperty("spring_boot_plugin_version")
    val springDependencyManagementPluginVersion = providers.gradleProperty("spring_dependency_management_plugin_version")
    val aspectjPluginVersion = providers.gradleProperty("aspectj_plugin_version")
    val lombokPluginVersion = providers.gradleProperty("lombok_plugin_version")
    val nodePluginVersion = providers.gradleProperty("node_plugin_version")
    plugins {
        id("org.springframework.boot").version(springBootPluginVersion.get())
        id("io.spring.dependency-management").version(springDependencyManagementPluginVersion.get())
        id("io.freefair.aspectj.post-compile-weaving").version(aspectjPluginVersion.get())
        id("io.freefair.lombok").version(lombokPluginVersion.get())
        id("com.github.node-gradle.node").version(nodePluginVersion.get())
    }
}