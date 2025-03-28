plugins {
    kotlin("jvm")
    id("com.google.devtools.ksp")
    application
}

// Allows running from command line using  ./gradlew :main-project:run
application {
    mainClass.set("io.radhikayusuf.MainKt")
}

// Makes generated code visible to IDE
kotlin.sourceSets.main {
    kotlin.srcDirs(
            file("$buildDir/generated/ksp/main/kotlin"),
    )
}

ksp {
    // Passing an argument to the symbol processor.
    // Change value to "true" in order to apply the argument.
    arg("ignoreGenericArgs", "false")
}

dependencies {
    implementation(project(":annotations"))
    ksp(project(":processor"))

    implementation("org.json:json:20210307")
    implementation("com.google.code.gson:gson:2.12.1")
}