import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    java
    id("org.springframework.boot") version "3.5.5"
    id("io.spring.dependency-management") version "1.1.7"
    kotlin("jvm")
}

group = "com.gutorov"
version = "0.0.1-SNAPSHOT"
description = "Demo University Spring Boot API"
var jdkVersion = "21"

defaultTasks("bootRun")

tasks {
    named<Jar>("jar") {
        enabled = false
    }

    named<BootJar>("bootJar") {
        archiveFileName.set("${project.name}-$version.jar")
    }
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
    set("springdocVersion", "2.8.12")
    set("h2Version", "2.4.240")
    set("mockitoVersion", "5.19.0")
}

val springdocVersion: String by extra
val h2Version: String by extra
val mockitoVersion: String by extra

configurations {
    create("mockitoAgent")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$springdocVersion")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.h2database:h2:$h2Version")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testImplementation("org.mockito:mockito-core:$mockitoVersion")
    "mockitoAgent"("org.mockito:mockito-core:$mockitoVersion") {
        isTransitive = false
    }
    implementation(kotlin("stdlib-jdk8"))
}

tasks.named<Test>("test") {
    useJUnitPlatform()
    jvmArgs("-Xshare:off")
    jvmArgs("-javaagent:${configurations["mockitoAgent"].asPath}")
}
