plugins {
    kotlin("jvm") version "1.3.71"
    id("java-library")
    maven
}

group = "dev.drzepka.smarthome"
version = "1.0.0"

allprojects {
    repositories {
        jcenter()
        mavenLocal()
    }

    apply(plugin = "org.jetbrains.kotlin.jvm")

    dependencies {
        implementation(kotlin("stdlib-jdk8"))

        compileOnly("com.fasterxml.jackson.core:jackson-core:2.11.0")
        compileOnly("com.fasterxml.jackson.core:jackson-databind:2.11.0")
        compileOnly("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.11.0")
    }
}

java {
    withSourcesJar()
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