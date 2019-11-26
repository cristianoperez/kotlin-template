import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("plugin.jpa") version "1.3.30"
    kotlin("jvm") version "1.3.30"
    id("org.jetbrains.kotlin.plugin.spring") version "1.3.30"
    id("org.springframework.boot") version "2.1.5.RELEASE"
    id("io.spring.dependency-management") version "1.0.7.RELEASE"
    id("net.researchgate.release") version "2.6.0"
    id("io.gitlab.arturbosch.detekt") version "1.0.1"
}

group = "br.com.creditas"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    jcenter()
}

val swaggerVersion = "2.9.2"
val detektVersion = "1.0.1"

dependencies {
//  Basic dependencies:
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("javax.activation:activation:1.1.1")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
//  Web dependencies:
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.springfox:springfox-swagger2:$swaggerVersion")
    implementation("io.springfox:springfox-swagger-ui:$swaggerVersion")
//  PostgreSQL dependencies:
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("org.postgresql:postgresql")
    implementation("org.flywaydb:flyway-core")
//  MongoDB dependencies:
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    testImplementation("de.flapdoodle.embed:de.flapdoodle.embed.mongo")
//  Log dependencies
    implementation("org.zalando:logbook-spring-boot-starter:1.13.0")
    implementation("com.logentries:logentries-appender:1.1.38")
//  Test and lint dependencies
    testImplementation("org.amshove.kluent:kluent:1.40")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.mockk:mockk:1.9.3")
    detekt("io.gitlab.arturbosch.detekt:detekt-formatting:$detektVersion")
    detekt("io.gitlab.arturbosch.detekt:detekt-cli:$detektVersion")
}

release {
    preTagCommitMessage = "[Gradle Release Plugin] [skip ci] - pre tag commit:"
    tagCommitMessage = "[Gradle Release Plugin] [skip ci] - creating tag:"
    newVersionCommitMessage = "[Gradle Release Plugin] [skip ci] - new version commit:"
}

tasks.withType<Test> {
    useJUnit()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

detekt {
    toolVersion = detektVersion
    input = files("./")
    config = files("./detekt-config.yml")
}
