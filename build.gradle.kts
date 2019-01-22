import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompile

buildscript {
    val kotlinVersion: String by extra("1.3.11")

    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
}

plugins {
    val kotlinVersion = "1.3.11"
    kotlin("jvm") version kotlinVersion
}

tasks.withType<KotlinJvmCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

val mainClassName = "aero.geosystems.formats.rtcm3.app.AppKt"

repositories {
    mavenCentral()
}

dependencies {
    compile("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testCompile("org.jetbrains.kotlin:kotlin-test")
    testCompile("org.jetbrains.kotlin:kotlin-test-junit")
    compile(project(":formats-core"))
    compile(project(":gnss-core"))
}

tasks.withType<Jar> {
    manifest {
        attributes(mapOf(
                "Class-Path" to configurations.compile.joinToString(" ") { it.name },
                "Main-Class" to mainClassName
        ))
    }
}
//create a single Jar with all dependencies
task<Jar>("fatJar") {
    manifest {
        attributes(mapOf("Main-Class" to mainClassName))
    }
    baseName = "Rtcm3App"
    from(
            configurations.compile.map {
                if (it.isDirectory) it else zipTree(it)
            }
    )
    with((tasks["jar"] as Jar))
}