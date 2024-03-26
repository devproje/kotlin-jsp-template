@file:Suppress("PropertyName")
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.23"
    java
    war
}

group = "net.projecttl"
version = "1.0.0-SNAPSHOT"

val junit_version: String by project
val exposed_version: String by project

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17

    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

repositories {
    mavenCentral()
    google()
}

dependencies {
    compileOnly("javax.servlet:javax.servlet-api:4.0.1")

    implementation("com.google.code.gson:gson:2.10.1")
	implementation("org.mariadb.jdbc:mariadb-java-client:3.3.3")
	implementation("org.jetbrains.exposed:exposed-core:$exposed_version")
	implementation("org.jetbrains.exposed:exposed-jdbc:$exposed_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")

    testImplementation("org.junit.jupiter:junit-jupiter-api:${junit_version}")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:${junit_version}")
}

tasks {
    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }

    test {
        useJUnitPlatform()
    }
}
