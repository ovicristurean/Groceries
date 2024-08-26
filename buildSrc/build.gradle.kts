repositories {
    mavenCentral()
}

plugins {
    `kotlin-dsl`
    kotlin("plugin.serialization") version "1.9.21"
}

dependencies {
    implementation("io.ktor:ktor-client-core:2.3.12")
    implementation("io.ktor:ktor-client-okhttp:2.3.12")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
}
