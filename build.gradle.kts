import com.github.gradle.node.npm.task.NpmTask
plugins {
    java
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("io.freefair.aspectj.post-compile-weaving")
    id("io.freefair.lombok")
    id("com.github.node-gradle.node")
}
base.archivesName = property("name") as String
group = property("group") as String
version = property("version") as String
repositories { mavenCentral() }
dependencies {
    implementation("org.springframework.boot", "spring-boot-starter-data-jpa")
    implementation("org.springframework.boot", "spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot", "spring-boot-starter-validation")
    implementation("org.springframework.boot", "spring-boot-starter-web")
    implementation("org.springframework.boot", "spring-boot-starter-log4j2")
    implementation("org.craftercms.mariaDB4j", "mariaDB4j-springboot", property("mariadb4j_spring_boot_version") as String)
    implementation("org.mariadb.jdbc", "mariadb-java-client", property("mariadb4j_driver_version") as String)
    testImplementation("org.springframework.boot", "spring-boot-starter-test")
    developmentOnly("org.springframework.boot" , "spring-boot-devtools")
    annotationProcessor("org.projectlombok" , "lombok")
    runtimeOnly("org.aspectj", "aspectjrt")
}
configurations { all { exclude("org.springframework.boot", "spring-boot-starter-logging") } }
node {
    download = true
    npmVersion = property("npm_version") as String
    version = property("nodejs_version") as String
}
tasks {
    val installTypeScript by registering(NpmTask::class) { args = listOf("install", "typescript", "--save-dev") }
    val compileTypeScript by registering(NpmTask::class) {
        dependsOn(installTypeScript)
        args = listOf("run", "tsc")
    }
    val javaVersion = JavaVersion.toVersion((property("java_version") as String).toInt())
    java {
        toolchain.languageVersion = JavaLanguageVersion.of(javaVersion.toString())
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }
    withType<JavaCompile>().configureEach {
        dependsOn(compileTypeScript)
        options.encoding = "UTF-8"
        sourceCompatibility = javaVersion.toString()
        targetCompatibility = javaVersion.toString()
        options.release = javaVersion.toString().toInt()
    }
    withType<JavaExec>().configureEach { defaultCharacterEncoding = "UTF-8" }
    withType<Javadoc>().configureEach { options.encoding = "UTF-8" }
    withType<Test>().configureEach {
        defaultCharacterEncoding = "UTF-8"
        useJUnitPlatform()
    }
}