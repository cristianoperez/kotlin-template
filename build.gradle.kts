import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("plugin.jpa") version "1.3.30"
	kotlin("jvm") version "1.3.30"
	id("org.jetbrains.kotlin.plugin.spring") version "1.3.30"
	id("org.springframework.boot") version "2.1.5.RELEASE"
	id("io.spring.dependency-management") version "1.0.7.RELEASE"
	id("net.researchgate.release") version "2.6.0"
}

group = "br.com.creditas"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

release {
	preTagCommitMessage = "[Gradle Release Plugin] [skip ci] - pre tag commit:"
	tagCommitMessage = "[Gradle Release Plugin] [skip ci] - creating tag:"
	newVersionCommitMessage = "[Gradle Release Plugin] [skip ci] - new version commit:"
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.flywaydb:flyway-core")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("ch.qos.logback:logback-core")
	implementation("com.logentries:logentries-appender:1.1.38")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.mockk:mockk:1.9.3")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
