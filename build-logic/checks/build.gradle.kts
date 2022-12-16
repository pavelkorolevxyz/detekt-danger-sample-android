plugins {
    `kotlin-dsl`
}

group = "buildlogic"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.22.0")
}
