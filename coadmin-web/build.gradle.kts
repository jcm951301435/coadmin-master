dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa") {
        exclude("org.hibernate:hibernate-entitymanager")
        exclude("org.hibernate:hibernate-core")
    }
    implementation("org.eclipse.persistence:org.eclipse.persistence.jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springdoc:springdoc-openapi-ui")
    runtimeOnly("mysql:mysql-connector-java")
}

tasks {
    bootJar {
        enabled = true
    }
    jar {
        enabled = true
    }
}