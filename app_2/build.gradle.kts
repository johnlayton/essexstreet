import io.micronaut.gradle.docker.NativeImageDockerfile
import org.jetbrains.kotlin.cli.jvm.main

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.21"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.9.21"
    id("com.google.devtools.ksp") version "1.9.21-1.0.15"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.micronaut.application") version "4.2.1"
//    id("org.graalvm.buildtools.native") version "0.9.12"
    id("maven-publish")
//    id("application")
}

version = "0.1"
group = "org.essexstreet"

val kotlinVersion = project.properties.get("kotlinVersion")

repositories {
    mavenCentral()
}

dependencies {
    ksp("io.micronaut.serde:micronaut-serde-processor")
    implementation("com.amazonaws:aws-lambda-java-events")
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut.aws:micronaut-aws-apigateway")
    implementation("io.micronaut.aws:micronaut-aws-lambda-events-serde")
    implementation("io.micronaut.aws:micronaut-function-aws")
    implementation("io.micronaut.aws:micronaut-function-aws-custom-runtime")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("io.micronaut.serde:micronaut-serde-jackson")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    runtimeOnly("ch.qos.logback:logback-classic")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")
}

application {
    mainClass.set("org.essexstreet.FunctionLambdaRuntime")
}

java {
    sourceCompatibility = JavaVersion.toVersion("17")
}

micronaut {
    runtime("lambda_provided")
    testRuntime("junit5")
    nativeLambda {
        lambdaRuntimeClassName.set("io.micronaut.function.aws.runtime.MicronautLambdaRuntime")
    }
    processing {
        incremental(true)
        annotations("org.essexstreet.*")
    }
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/johnlayton/essexstreet")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("TOKEN")
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])
        }
    }
}

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

tasks.named<NativeImageDockerfile>("dockerfileNative") {
    baseImage.set("amazonlinux:2")
    jdkVersion.set("17")
    args(
        "-XX:MaximumHeapSizePercent=80",
        "-Dio.netty.allocator.numDirectArenas=0",
        "-Dio.netty.noPreferDirect=true"
    )
}

