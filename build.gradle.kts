import com.github.gradle.node.npm.task.NpmTask
plugins {
    java
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
    alias(libs.plugins.aspectj)
    alias(libs.plugins.lombok)
    alias(libs.plugins.node)
}
val projectName = providers.gradleProperty("name")
val projectGroup = providers.gradleProperty("group")
val projectVersion = providers.gradleProperty("version")
val javaVersion = libs.versions.java.map { it.toInt() }
base.archivesName = projectName
group = projectGroup.get()
version = projectVersion.get()
repositories { mavenCentral() }
val mockitoAgent by configurations.creating
dependencies {
    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.spring.boot.starter.jooq)
    implementation(libs.spring.boot.starter.thymeleaf)
    implementation(libs.spring.boot.starter.validation)
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.undertow)
    implementation(libs.spring.boot.starter.log4j2)
    implementation(libs.embeddedPostgres)
    implementation(enforcedPlatform(libs.postgresql))
    implementation(libs.postgresqlDriver)
    implementation(libs.jbossThreads)
    implementation(libs.aspectjrt)
    testImplementation(libs.spring.boot.starter.test)
    developmentOnly(libs.spring.boot.devtools)
    annotationProcessor(libs.lombok)
    mockitoAgent(libs.mockito) { isTransitive = false }
}
configurations {
    all {
        libs.spring.boot.starter.logging.get().apply { this@all.exclude(group, name) }
        libs.spring.boot.starter.tomcat.get().apply { this@all.exclude(group, name) }
    }
}
node {
    download = true
    npmVersion = libs.versions.npm
    version = libs.versions.nodejs
}
abstract class MockitoAgentArgs @Inject constructor(objects: ObjectFactory) : CommandLineArgumentProvider {
    @get:InputFiles
    @get:PathSensitive(PathSensitivity.RELATIVE)
    val agentFiles: ConfigurableFileCollection = objects.fileCollection()
    override fun asArguments(): Iterable<String> = agentFiles.files.map { "-javaagent:${it.absolutePath}" }
}
java {
    toolchain {
        languageVersion = javaVersion.map { JavaLanguageVersion.of(it) }
        vendor = JvmVendorSpec.ADOPTIUM
    }
    sourceCompatibility = JavaVersion.toVersion(javaVersion.get())
    targetCompatibility = JavaVersion.toVersion(javaVersion.get())
}
tasks {
    val compileTypeScript by registering(NpmTask::class) {
        dependsOn(npmInstall)
        args = listOf("run", "tsc")
    }
    withType<JavaCompile>().configureEach {
        dependsOn(compileTypeScript)
        options.encoding = "UTF-8"
        sourceCompatibility = javaVersion.get().toString()
        targetCompatibility = javaVersion.get().toString()
        if (javaVersion.get() > 8) options.release = javaVersion
    }
    named<UpdateDaemonJvm>("updateDaemonJvm") {
        languageVersion = libs.versions.gradleJava.map { JavaLanguageVersion.of(it.toInt()) }
        vendor = JvmVendorSpec.ADOPTIUM
    }
    withType<JavaExec>().configureEach { defaultCharacterEncoding = "UTF-8" }
    withType<Javadoc>().configureEach { options.encoding = "UTF-8" }
    withType<Test>().configureEach {
        val provider = objects.newInstance(MockitoAgentArgs::class.java).apply { agentFiles.from(mockitoAgent) }
        jvmArgumentProviders.add(provider)
        defaultCharacterEncoding = "UTF-8"
        useJUnitPlatform()
    }
}