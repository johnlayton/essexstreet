rootProject.name = "essexstreet"

//plugins {
//    id 'io.micronaut.platform.catalog' version '4.4.0'
//}

pluginManagement {
    plugins {
        id("io.micronaut.platform.catalog") version "4.4.0"
        id("fr.brouillard.oss.gradle.jgitver") version "0.9.1"
    }
//    plugins {
//    }
    resolutionStrategy {
    }
    repositories {
    }
}

plugins {
    id("io.micronaut.platform.catalog")
//    id("io.micronaut.platform.catalog") version "4.4.0"
//    id("fr.brouillard.oss.gradle.jgitver") version "0.9.1"
}

//include("cli")
include("lib")
//include("lib_2")
include("app_1")
//include("app_2")
//include("app_3")
include("infra")
