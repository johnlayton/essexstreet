import io.micronaut.gradle.docker.NativeImageDockerfile

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.21"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.9.21"
    id("com.google.devtools.ksp") version "1.9.21-1.0.15"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.micronaut.application") version "4.4.0"
    id("io.micronaut.aot") version "4.4.0"
/*
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.micronaut.application") version "4.2.0"
*/
}

val kotlinVersion = project.properties.get("kotlinVersion")

version = "0.1"
group = "org.essexstreet"

repositories {
    mavenCentral()
}

dependencies {
    ksp("info.picocli:picocli-codegen")
    ksp("io.micronaut.serde:micronaut-serde-processor")
    implementation("com.nimbusds:nimbus-jose-jwt:9.25")
    implementation("info.picocli:picocli")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("io.micronaut.picocli:micronaut-picocli")
    implementation("io.micronaut.serde:micronaut-serde-jackson")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    runtimeOnly("ch.qos.logback:logback-classic")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")
//
//    annotationProcessor("info.picocli:picocli-codegen")
//    annotationProcessor("io.micronaut.serde:micronaut-serde-processor")
//    implementation("info.picocli:picocli")
//    implementation("io.micronaut.picocli:micronaut-picocli")
//    implementation("io.micronaut.serde:micronaut-serde-jackson")
//    runtimeOnly("ch.qos.logback:logback-classic")
}

application {
    mainClass.set("org.essexstreet.MicronautguideCommand")
}

java {
    sourceCompatibility = JavaVersion.toVersion("17")
    targetCompatibility = JavaVersion.toVersion("17")
}

micronaut {
//    runtime("none")
    testRuntime("junit5")
//    nativeLambda {
//        lambdaRuntimeClassName.set("io.micronaut.function.aws.runtime.MicronautLambdaRuntime")
//    }
//    processing {
//        incremental(true)
//        annotations("org.essexstreet.*")
//    }
//    testRuntime("junit5")
    processing {
        incremental(true)

        annotations("org.essexstreet.*")
    }
}

//tasks.named<NativeImageDockerfile>("dockerfileNative") {
//    baseImage.set("ubuntu:latest")
//    jdkVersion.set("17")
//    args(
//        "-XX:MaximumHeapSizePercent=80",
//        "-Dio.netty.allocator.numDirectArenas=0",
//        "-Dio.netty.noPreferDirect=true"
//    )
//}

//plugins {
//    id("org.jetbrains.kotlin.jvm") version "1.9.21"
//    id("org.jetbrains.kotlin.plugin.allopen") version "1.9.21"
//    id("com.google.devtools.ksp") version "1.9.21-1.0.15"
//    id("com.github.johnrengelman.shadow") version "8.1.1"
//    id("io.micronaut.application") version "4.2.1"
//}
//
//version = "0.1"
//group = "example.micronaut"
//
//repositories {
//    mavenCentral()
//}
//
//dependencies {
//    ksp("info.picocli:picocli-codegen")
//    ksp("io.micronaut.serde:micronaut-serde-processor")
//    implementation("com.nimbusds:nimbus-jose-jwt:9.25")
//    implementation("info.picocli:picocli")
//    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
//    implementation("io.micronaut.picocli:micronaut-picocli")
//    implementation("io.micronaut.serde:micronaut-serde-jackson")
//    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
//    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
//    runtimeOnly("ch.qos.logback:logback-classic")
//    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")
//}
//
//
//application {
//    mainClass.set("example.micronaut.DefaultCommand")
//}
//java {
//    sourceCompatibility = JavaVersion.toVersion("17")
//}
//
//
//micronaut {
//    testRuntime("junit5")
//    processing {
//        incremental(true)
//        annotations("example.micronaut.*")
//    }
//}
//

graalvmNative {
    toolchainDetection.set(false)
//    binaries {
//        main {
//
//            imageName.set('mn-graalvm-application')
//        }
//    }
//    binaries {
//        main {
//            imageName.set('mn-graalvm-application')
////            buildArgs.add('--verbose')
//        }
//    }
}

//tasks.named<NativeImageDockerfile>("dockerfileNative") {
////    baseImage.set("amazonlinux:2")
//    jdkVersion.set("17")
//    args(
//        "-XX:MaximumHeapSizePercent=80",
//        "-Dio.netty.allocator.numDirectArenas=0",
//        "-Dio.netty.noPreferDirect=true"
//    )
//}

graalvmNative.toolchainDetection = false
micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("com.example.*")
    }
    aot {
        // Please review carefully the optimizations enabled below
        // Check https://micronaut-projects.github.io/micronaut-aot/latest/guide/ for more details
        optimizeServiceLoading = false
        convertYamlToJava = false
        precomputeOperations = true
        cacheEnvironment = true
        optimizeClassLoading = true
        deduceEnvironment = true
        optimizeNetty = true
        replaceLogbackXml = true
    }
}


tasks.named<io.micronaut.gradle.docker.NativeImageDockerfile>("dockerfileNative") {
    jdkVersion = "17"
}