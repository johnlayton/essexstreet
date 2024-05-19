rootProject.name = "essexstreet"

//plugins {
//    id 'io.micronaut.platform.catalog' version '4.4.0'
//}

pluginManagement {
    plugins {
        id("io.micronaut.platform.catalog") version "4.4.0"
    }
//    plugins {
//    }
    resolutionStrategy {
    }
    repositories {
    }
}

//include("cli")
include("lib")
include("app_1")
//include("app_2")
include("infra")
