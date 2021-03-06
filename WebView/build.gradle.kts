import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.5.6"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.5.31"
    kotlin("plugin.spring") version "1.5.31"
}

group = "com.faustofan"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("de.codecentric:spring-boot-admin-starter-client:2.5.3")
    implementation("org.springframework.boot:spring-boot-starter-actuator:2.5.5")
    implementation("org.springframework.boot:spring-boot-starter-data-redis:2.5.6")
    runtimeOnly("mysql:mysql-connector-java:8.0.25")
    implementation("com.baomidou:mybatis-plus-boot-starter:3.4.3.4")
    implementation("com.alibaba:druid-spring-boot-starter:1.2.8")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.5.5")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf:2.5.5")
    implementation("org.springframework.boot:spring-boot-starter-web:2.5.5")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.0")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    compileOnly("org.projectlombok:lombok:1.18.22")
    developmentOnly("org.springframework.boot:spring-boot-devtools:2.5.5")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:2.5.5")
    annotationProcessor("org.projectlombok:lombok:1.18.22")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.5.5")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}