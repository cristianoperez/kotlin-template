import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    val kotlinVersion = "1.3.31"

    repositories {
        jcenter()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${kotlinVersion}")
        classpath("org.jetbrains.kotlin:kotlin-allopen:${kotlinVersion}")
    }
}

plugins {
    kotlin("jvm") version "1.3.31"
    id("io.gitlab.arturbosch.detekt") version "1.0.0.RC8"
}

group = "br.com.creditas"
version = "1.0-SNAPSHOT"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

allprojects {
    repositories {
        jcenter()
    }

    apply {
        plugin("kotlin")
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
}


detekt {
    version = "1.0.0.RC8"
    defaultProfile {
        input = "./"
        config = "./detekt-config.yml"
    }
}
