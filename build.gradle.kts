plugins {
    java
    id("org.springframework.boot") version "3.5.5"
    id("io.spring.dependency-management") version "1.1.7"
    id("com.github.node-gradle.node") version "7.1.0"
    id("org.liquibase.gradle") version "2.2.2"
    kotlin("jvm") version "2.2.0"
}

group = "com.gutorov"
version = "0.0.1-SNAPSHOT"
description = "Demo University Spring Boot API"
var jdkVersion = "21"

defaultTasks("bootRun")

tasks.jar {
    enabled = false
}

tasks.bootJar {
    archiveFileName.set("${project.name}-$version.jar")
}

require(System.getProperty("java.specification.version") == jdkVersion) {
    "Wrong JDK version"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(jdkVersion))
    }
}

repositories {
    mavenCentral()
}

extra.apply {
    set("liquibaseVersion", "4.33.0")
}

val springdocVersion = "2.8.12"
val h2Version = "2.4.240"
val postgresVersion = "42.7.8"
val liquibaseVersion: String by extra
val mockitoVersion = "5.19.0"

val springProfiles = mutableListOf<String>().apply {
    if (project.hasProperty("front"))
        add("front")
    if (project.hasProperty("prod"))
        add("prod")
    else
        add("dev")
}

val currentProfiles = springProfiles.joinToString(", ")
logger.quiet("Current profiles: $currentProfiles")

configurations {
    create("mockitoAgent")
}

if ("front" in springProfiles)
    apply(from = "build.front.gradle.kts")
if ("dev" in springProfiles)
    apply(from = "build.migrations.gradle")

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$springdocVersion")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("org.liquibase:liquibase-core:$liquibaseVersion")

    if ("prod" in springProfiles)
        runtimeOnly("org.postgresql:postgresql:$postgresVersion")
    else {
        runtimeOnly("org.postgresql:postgresql:${postgresVersion}")
        runtimeOnly("com.h2database:h2:$h2Version")
    }

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testImplementation("org.mockito:mockito-core:$mockitoVersion")
    "mockitoAgent"("org.mockito:mockito-core:$mockitoVersion") {
        isTransitive = false
    }

    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlin:kotlin-reflect")
}

tasks.bootRun {
    val currentArgs = mutableListOf("--spring.profiles.active=$currentProfiles")
    if (project.hasProperty("args"))
        currentArgs.addAll(
            (project.property("args") as String)
                .split(Regex("\\s+")).filter { it.isNotBlank() })
    args(currentArgs)
}

tasks.test {
    useJUnitPlatform()
    jvmArgs("-Xshare:off")
    jvmArgs("-javaagent:${configurations["mockitoAgent"].asPath}")
    systemProperty("spring.profiles.active", currentProfiles)
}

tasks.processResources {
    filesMatching("**/application.yml") {
        filter<org.apache.tools.ant.filters.ReplaceTokens>("tokens" to mapOf("active: dev" to "active: $currentProfiles"))
        expand("active" to currentProfiles)
    }
}