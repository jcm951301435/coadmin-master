plugins {
    id("org.springframework.boot") version "2.5.0"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    java
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

allprojects {
    repositories {
        maven {
            url = uri("https://maven.aliyun.com/repository/public/")
        }
        mavenLocal()
        mavenCentral()
    }

    group = "com.java"
    version = "1.0.0"

    apply(plugin = "java")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    dependencyManagement {
        dependencies {
            dependency("org.springdoc:springdoc-openapi-ui:1.5.7")
            dependency("org.eclipse.persistence:org.eclipse.persistence.jpa:2.7.8")
            dependency("cn.hutool:hutool-all:5.3.8")
            dependency("com.nimbusds:nimbus-jose-jwt:9.8.1")
        }
    }

    tasks {
        test {
            useJUnitPlatform()
        }
        bootJar {
            enabled = false
        }
        jar {
            enabled = false
        }
    }
}

subprojects {
    dependencies {
        implementation("cn.hutool:hutool-all")
        implementation("com.nimbusds:nimbus-jose-jwt")
        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")
        developmentOnly("org.springframework.boot:spring-boot-devtools")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
    }
}
