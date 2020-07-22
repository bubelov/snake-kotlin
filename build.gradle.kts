plugins {
    kotlin("jvm")
}

group = "com.bubelov"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:${findProperty("coroutinesVersion")}")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}