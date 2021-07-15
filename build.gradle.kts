plugins {
    kotlin("jvm") version "1.3.71"
    kotlin("plugin.allopen") version "1.3.71"
    id("java-library")

    maven
    id("maven-publish")
}

group = "dev.drzepka.smarthome"
version = "1.1.0"

allprojects {
    repositories {
        jcenter()
        mavenLocal()
    }

    apply(plugin = "org.jetbrains.kotlin.jvm")

    dependencies {
        implementation(kotlin("stdlib-jdk8"))

        compileOnly("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9")
        compileOnly("com.fasterxml.jackson.core:jackson-core:2.11.0")
        compileOnly("com.fasterxml.jackson.core:jackson-databind:2.11.0")
        compileOnly("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.11.0")
        compileOnly("org.slf4j:slf4j-api:1.7.31")

        testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.9")
        testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.1")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.1")
        testImplementation("org.assertj:assertj-core:3.19.0")
        testImplementation("org.mockito:mockito-core:3.9.0")
        testImplementation("org.mockito:mockito-junit-jupiter:3.9.0")
        testImplementation("org.mockito.kotlin:mockito-kotlin:3.1.0")

        testImplementation("ch.qos.logback:logback-classic:1.2.1")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

java {
    withSourcesJar()
}

allOpen {
    annotation("dev.drzepka.smarthome.common.util.Mockable")
}

artifacts {
    archives(tasks.getByName("sourcesJar"))
}

tasks.jar {
    subprojects.forEach { subproject ->
        from(subproject.sourceSets.main.get().output.classesDirs)
        from(subproject.sourceSets.main.get().output.resourcesDir)
    }
}

tasks.getByName<Jar>("sourcesJar") {
    subprojects.forEach { subproject ->
        from(subproject.sourceSets.main.get().allSource)
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
    repositories {
        maven {
            setUrl("https://gitlab.com/api/v4/projects/21177602/packages/maven")
            credentials(HttpHeaderCredentials::class) {
                val ciToken = System.getenv("CI_JOB_TOKEN")
                val privateToken = findProperty("gitLabPrivateToken") as String? // from ~/.gradle/gradle.properties

                when {
                    ciToken != null -> {
                        name = "Job-Token"
                        value = ciToken
                    }
                    privateToken != null -> {
                        name = "Private-Token"
                        value = privateToken
                    }
                    else -> {
                        logger.warn("Neither job nor private token were defined, publishing will fail")
                    }
                }
            }
            authentication {
                create<HttpHeaderAuthentication>("header")
            }
        }
    }
}