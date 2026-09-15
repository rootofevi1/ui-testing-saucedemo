plugins {
    java
    id("io.qameta.allure") version "4.1.0"
}

group = "com.saucedemo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

val cucumberVersion = "7.34.4"
val allureVersion = "2.35.2"

dependencies {
    implementation("com.codeborne:selenide:7.17.0")
    implementation("io.cucumber:cucumber-java:$cucumberVersion")

    testImplementation(platform("io.qameta.allure:allure-bom:$allureVersion"))
    testImplementation("io.qameta.allure:allure-cucumber7-jvm")
    testImplementation("io.qameta.allure:allure-junit-platform")

    testImplementation("org.junit.jupiter:junit-jupiter:6.0.1")
    testImplementation("io.cucumber:cucumber-junit-platform-engine:$cucumberVersion")
    testImplementation("org.junit.platform:junit-platform-suite:6.0.1")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

allure {
    version = "2.42.1"

    adapter {
        allureJavaVersion.set(allureVersion)
        aspectjWeaver.set(false)
    }
}

tasks.test {
    useJUnitPlatform()

    val browser = providers.gradleProperty("browser").orElse("chrome").get()
    val browserSize = providers.gradleProperty("browserSize").orElse("1920x1080").get()
    val headless = providers.gradleProperty("headless").orElse("true").get()
    val timeout = providers.gradleProperty("timeout").orElse("10000").get()
    val testUrl = providers.gradleProperty("testUrl").orElse("https://www.saucedemo.com").get()

    jvmArgs(
        "-Dbrowser=$browser",
        "-DbrowserSize=$browserSize",
        "-Dheadless=$headless",
        "-Dtimeout=$timeout",
        "-DtestUrl=$testUrl"
    )

    testLogging {
        events("passed", "skipped", "failed")
        showStandardStreams = true
        showExceptions = true
        showCauses = true
        showStackTraces = true
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
    }
}
