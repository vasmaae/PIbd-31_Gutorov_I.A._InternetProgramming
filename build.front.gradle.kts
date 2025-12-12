apply(plugin = "com.github.node-gradle.node")

logger.quiet("Configure front builder")

val frontDir = file("${project.projectDir}/frontend")

if (!frontDir.exists()) {
    throw GradleException("Frontend app directory does not exist: $frontDir")
}

logger.quiet("Webapp dir is $frontDir")

node {
    version.set("22.17.1")
    npmVersion.set("10.9.2")
    download.set(true)
}

tasks.register<NpmTask>("frontDepsInstall") {
    group = "front"
    description = "Installs dependencies from package.json"
    logger.quiet(this.description)

    workingDir.set(frontDir)
    args.set(listOf("install"))
}

tasks.register<NpmTask>("frontBuild") {
    group = "front"
    description = "Build frontend webapp"
    logger.quiet(this.description)

    workingDir.set(frontDir)
    dependsOn("frontDepsInstall")
    args.set(listOf("run", "build"))
}

if (frontDir.exists()) {
    tasks.named("processResources") {
        finalizedBy("frontBuild")
    }
}