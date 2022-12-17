
plugins {
    id("java")
    `kotlin-dsl`
    application
    id("nu.studer.jooq") version "8.0" apply false
}

group = "ru.girmank.vk"
version = "1.0-SNAPSHOT"

allprojects {
    repositories {
        mavenCentral()
        google()
    }
}

subprojects {

    apply {
        plugin("org.jetbrains.kotlin.jvm")
        plugin("nu.studer.jooq")
    }

    dependencies {
        // https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api
        compileOnly("javax.servlet:javax.servlet-api:4.0.1")

        // https://mvnrepository.com/artifact/org.jboss.resteasy/resteasy-guice
        implementation("org.jboss.resteasy:resteasy-guice:4.5.8.Final")
        // https://mvnrepository.com/artifact/org.jboss.resteasy/resteasy-jackson2-provider
        implementation("org.jboss.resteasy:resteasy-jackson2-provider:4.5.8.Final")

        // https://mvnrepository.com/artifact/org.eclipse.jetty/jetty-servlet
        implementation("org.eclipse.jetty:jetty-servlet:9.4.33.v20201020")

        // https://mvnrepository.com/artifact/com.google.inject/guice
        implementation("com.google.inject:guice:5.1.0")

        implementation("org.jooq:jooq:3.17.4")
        implementation("org.jooq:jooq-codegen:3.17.4")
        implementation("org.jooq:jooq-meta:3.17.4")

        implementation("com.intellij:annotations:12.0")

        implementation("org.flywaydb:flyway-core:9.7.0")
        implementation("org.postgresql:postgresql:42.5.0")
    }

    tasks.getByName<Test>("test") {
        useJUnitPlatform()
    }
}
