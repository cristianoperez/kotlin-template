import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.31"
    id("io.gitlab.arturbosch.detekt") version "1.0.0.RC8"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.3.31"
}

group = "br.com.creditas"
version = "1.0-SNAPSHOT"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("javax.activation:activation:1.1.1")
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
