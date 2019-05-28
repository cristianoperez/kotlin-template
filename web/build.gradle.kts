val springBootVersion = "2.1.5.RELEASE"

plugins {
	id("org.springframework.boot") version "2.1.5.RELEASE"
	id("io.spring.dependency-management") version "1.0.7.RELEASE"
	kotlin("plugin.spring")
}

group = "br.com.creditas.riskanalysis"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

dependencies {
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
	implementation("org.zalando:logbook-spring-boot-starter:1.13.0")
    implementation("com.logentries:logentries-appender:1.1.38")
    testImplementation("org.mockito:mockito-core:2.19.0")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.0.0")
    testImplementation("org.amshove.kluent:kluent:1.40")
    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
}
