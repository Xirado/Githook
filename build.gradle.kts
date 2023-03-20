import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.20"
    kotlin("plugin.serialization") version "1.7.20"
    id("com.github.johnrengelman.shadow") version "7.1.1"
    application
}

application {
    mainClass.set("at.xirado.githook.Main")

    tasks.run.get().workingDir = File(rootProject.projectDir, "build/libs")
}

group = "at.xirado"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {
    implementation("org.athena:athena-core:1.0.2")
    implementation("ch.qos.logback:logback-classic:1.4.5")
    implementation("club.minnced:discord-webhooks:0.8.2")

    implementation("io.ktor:ktor-server-core:2.2.4")
    implementation("io.ktor:ktor-server-netty:2.2.4")
    implementation("io.ktor:ktor-server-content-negotiation:2.2.4")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.2.4")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json-jvm:1.5.0")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.apply {
        jvmTarget = "16"
        freeCompilerArgs += "-Xcontext-receivers"
    }

}