import com.github.gradle.node.npm.task.NpmTask
plugins {
    java
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("io.freefair.aspectj.post-compile-weaving")
    id("io.freefair.lombok")
    id("com.github.node-gradle.node")
}
val name = providers.gradleProperty("name")
val projectGroup = providers.gradleProperty("group")
val projectVersion = providers.gradleProperty("version")
val mariadb4jSpringBootVersion = providers.gradleProperty("mariadb4j_spring_boot_version")
val mariadb4jDriverVersion = providers.gradleProperty("mariadb4j_driver_version")
val projectNpmVersion = providers.gradleProperty("npm_version")
val nodejsVersion = providers.gradleProperty("nodejs_version")
val javaVersion = providers.gradleProperty("java_version")
base.archivesName = name.get()
group = projectGroup.get()
version = projectVersion.get()
repositories { mavenCentral() }
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-log4j2")
    implementation("ch.vorburger.mariaDB4j:mariaDB4j-springboot:${mariadb4jSpringBootVersion.get()}")
    implementation("org.mariadb.jdbc:mariadb-java-client:${mariadb4jDriverVersion.get()}")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.projectlombok:lombok")
    runtimeOnly("org.aspectj:aspectjrt")
}
configurations { all { exclude("org.springframework.boot", "spring-boot-starter-logging") } }
node {
    download = true
    npmVersion = projectNpmVersion.get()
    version = nodejsVersion.get()
}
tasks {
    val installTypeScript by registering(NpmTask::class) { args = listOf("install", "typescript", "--save-dev") }
    val compileTypeScript by registering(NpmTask::class) {
        dependsOn(installTypeScript)
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
        defaultCharacterEncoding = "UTF-8"
        useJUnitPlatform()
    }
}