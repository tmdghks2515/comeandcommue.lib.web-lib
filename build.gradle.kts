plugins {
	id("java-library")
	id("io.spring.dependency-management") version "1.1.7"
	id("org.springframework.boot") version "3.5.0"
	id("maven-publish")
}

group = "io.comeandcommue.lib"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

publishing {
	repositories {
		maven {
			name = "GitHubPackages"
			url = uri("https://maven.pkg.github.com/tmdghks2515/comeandcommue.lib.web-lib")
			credentials {
				 username = (findProperty("gpr.user") as String?) ?: System.getenv("GITHUB_ACTOR") ?: ""
				 password = (findProperty("gpr.key") as String?) ?: System.getenv("GITHUB_TOKEN") ?: ""
			}
		}
	}
	publications {
		create<MavenPublication>("mavenJava") {
			from(components["java"])
		}
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.auth0:java-jwt:4.4.0")
	compileOnly("org.springframework.boot:spring-boot-starter-data-jpa:3.5.0")
	compileOnly("org.projectlombok:lombok:1.18.30")
	annotationProcessor("org.projectlombok:lombok:1.18.30")
}

tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
	enabled = false
}

tasks.named<org.gradle.jvm.tasks.Jar>("jar") {
	enabled = true
}
