import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    groovy
    java
    kotlin("jvm") version "1.3.21"
}

group = "io.issenn.jenkins"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compile("org.codehaus.groovy:groovy-all:2.3.11")
    implementation(kotlin("stdlib-jdk8"))
    testCompile("junit", "junit", "4.12")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}