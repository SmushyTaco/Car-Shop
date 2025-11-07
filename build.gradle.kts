import com.github.gradle.node.npm.task.NpmTask
plugins {
    java
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("io.freefair.aspectj.post-compile-weaving")
    id("io.freefair.lombok")
    id("com.github.node-gradle.node")
}
val projectName = providers.gradleProperty("name")
val projectGroup = providers.gradleProperty("group")
val projectVersion = providers.gradleProperty("version")
val projectNpmVersion = providers.gradleProperty("npm_version")
val lombokVersion = providers.gradleProperty("lombok_version")
val aspectjrtVersion = providers.gradleProperty("aspectjrt_version")
val embeddedPostgresVersion = providers.gradleProperty("embedded_postgres_version")
val postgresqlVersion = providers.gradleProperty("postgresql_version")
val postgresqlDriverVersion = providers.gradleProperty("postgresql_driver_version")
val jbossThreadsVersion = providers.gradleProperty("jboss_threads_version")
val nodejsVersion = providers.gradleProperty("nodejs_version")
val javaVersion = providers.gradleProperty("java_version")
base.archivesName = projectName.get()
group = projectGroup.get()
version = projectVersion.get()
repositories { mavenCentral() }
val mockitoAgent by configurations.creating
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-jooq")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-undertow")
    implementation("org.springframework.boot:spring-boot-starter-log4j2")
    implementation("io.zonky.test:embedded-postgres:${embeddedPostgresVersion.get()}")
    implementation(enforcedPlatform("io.zonky.test.postgres:embedded-postgres-binaries-bom:${postgresqlVersion.get()}"))
    implementation("org.postgresql:postgresql:${postgresqlDriverVersion.get()}")
    implementation("org.jboss.threads:jboss-threads:${jbossThreadsVersion.get()}")
    implementation("org.aspectj:aspectjrt:${aspectjrtVersion.get()}")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.projectlombok:lombok:${lombokVersion.get()}")
    mockitoAgent("org.mockito:mockito-core") { isTransitive = false }
}
configurations {
    all {
        exclude("org.springframework.boot", "spring-boot-starter-logging")
        exclude("org.springframework.boot", "spring-boot-starter-tomcat")
    }
}
node {
    download = true
    npmVersion = projectNpmVersion.get()
    version = nodejsVersion.get()
}
abstract class MockitoAgentArgs @Inject constructor(objects: ObjectFactory) : CommandLineArgumentProvider {
    @get:InputFiles
    @get:PathSensitive(PathSensitivity.RELATIVE)
    val agentFiles: ConfigurableFileCollection = objects.fileCollection()
    override fun asArguments(): Iterable<String> = agentFiles.files.map { "-javaagent:${it.absolutePath}" }
}
tasks {
    val compileTypeScript by registering(NpmTask::class) {
        dependsOn(npmInstall)
        args = listOf("run", "tsc")
    }
    java {
        toolchain.languageVersion = JavaLanguageVersion.of(javaVersion.get())
        sourceCompatibility = JavaVersion.toVersion(javaVersion.get().toInt())
        targetCompatibility = JavaVersion.toVersion(javaVersion.get().toInt())
    }
    withType<JavaCompile>().configureEach {
        dependsOn(compileTypeScript)
        options.encoding = "UTF-8"
        sourceCompatibility = javaVersion.get()
        targetCompatibility = javaVersion.get()
        options.release = javaVersion.get().toInt()
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