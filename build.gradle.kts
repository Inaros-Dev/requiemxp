plugins {
    id("java")
}

group = "com.inarosdev"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_25
    targetCompatibility = JavaVersion.VERSION_25
}

repositories {
    mavenCentral()
}

dependencies {
    compileOnly(files("libs/HytaleServer.jar"))
}

tasks.jar {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    archiveBaseName.set("RequiemXP")
    archiveVersion.set(version.toString())
}

// Task to copy to mods folder
tasks.register<Copy>("deployToServer") {
    dependsOn(tasks.jar)
    from(tasks.jar.get().archiveFile)
    into(file("../server/mods")) // Adjust path to your server
}