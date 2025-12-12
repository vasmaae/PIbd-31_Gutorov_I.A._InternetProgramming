apply(plugin = "org.liquibase.gradle")

logger.quiet("Configure migrations generator")

val picocliVersion = "4.7.7"
val timestamp = java.time.LocalDateTime.now().format(
    java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmmss")
)

liquibase {
    activities.register("main") {
        changelogFile.set("db/master.yml")
        url.set("jdbc:h2:file:./data")
        username.set("university")
        password.set("university")
        referenceUrl.set(
            "hibernate:spring:com.gutorov.university.entity?dialect=org.hibernate.dialect.H2Dialect"
        )
        logLevel.set("warn")
    }

    runList.set(listOf("main"))
}

dependencies {
    liquibaseRuntime("org.liquibase.ext:liquibase-hibernate6:${liquibaseVersion}")
    liquibaseRuntime("info.picocli:picocli:$picocliVersion")
    liquibaseRuntime(sourceSets["main"].runtimeClasspath)
    liquibaseRuntime(sourceSets["main"].output)
}

tasks.named<org.liquibase.gradle.LiquibaseTask>("update") {
    dependsOn(tasks.processResources)
}

tasks.register("generateFull") {
    group = "migrations"
    description = "Generate full changelog from current database state"

    doFirst {
        liquibase {
            activities.register("main") {
                changeLogFile.set("src/main/resources/db/generated-full-${timestamp}.yml")
            }
        }
    }

    finalizedBy("generateChangelog")
}

tasks.register("generateDiff") {
    group = "liquibase"
    description = "Generate diff changelog between JPA entities and database"

    doFirst {
        liquibase {
            activities.register("main") {
                changeLogFile.set("src/main/resources/db/generated-diff-${timestamp}.yml")
            }
        }
    }

    finalizedBy("diffChangelog")
}

tasks.named<org.liquibase.gradle.LiquibaseTask>("diffChangelog") {
    dependsOn(tasks.compileJava)
}