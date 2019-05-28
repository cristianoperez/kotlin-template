import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

repositories {
    jcenter()
}

plugins {
    kotlin("jvm") version "1.3.31"
    id("io.gitlab.arturbosch.detekt") version "1.0.0.RC8"
    id("org.springframework.boot") version "2.1.5.RELEASE"
	id("io.spring.dependency-management") version "1.0.7.RELEASE"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.3.31"
	kotlin("plugin.spring") version "1.2.71"
}

apply {
    plugin("kotlin")
}

val springBootVersion = "2.1.5.RELEASE"

group = "br.com.creditas"
version = "1.0-SNAPSHOT"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("javax.activation:activation:1.1.1")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-actuator:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.springfox:springfox-swagger2:2.9.2")
    implementation("io.springfox:springfox-swagger-ui:2.9.2")
    implementation("org.springframework.boot:spring-boot-starter-jdbc:$springBootVersion")
    runtimeOnly("org.postgresql:postgresql:42.2.5.jre7")
    implementation("org.flywaydb:flyway-core:5.2.4")
    implementation("org.zalando:logbook-core:1.11.1")
    implementation("org.zalando:logbook-spring-boot-starter:1.11.1")
    implementation("org.slf4j:slf4j-api:1.7.25")
    implementation("ch.qos.logback:logback-classic:1.2.3")
    implementation("com.logentries:logentries-appender:1.1.38")
    testImplementation("org.mockito:mockito-core:2.19.0")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.0.0")
    testImplementation("org.amshove.kluent:kluent:1.40")
    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
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
    version = "1.0.0.RC8"
    defaultProfile {
        input = "./"
        config = "./detekt-config.yml"
    }
}
