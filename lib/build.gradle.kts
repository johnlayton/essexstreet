import fr.brouillard.oss.jgitver.Strategies

//plugins {
//    id("com.github.johnrengelman.shadow") version "8.1.1"
//    id("io.micronaut.application") version "4.3.8"
//    id("io.micronaut.aot") version "4.3.8"
//}

//import io.micronaut.gradle.docker.NativeImageDockerfile

plugins {
//    id("org.jetbrains.kotlin.jvm") version "1.9.21"
//    id("org.jetbrains.kotlin.plugin.allopen") version "1.9.21"
//    id("com.google.devtools.ksp") version "1.9.21-1.0.15"
//    id("com.github.johnrengelman.shadow") version "8.1.1"
//    id("io.micronaut.application") version "4.2.1"
//    id("io.micronaut.aot") version "4.3.8"
//    id("org.graalvm.buildtools.native") version "0.9.12"
    id("io.micronaut.library") version "4.4.2"
    id("java-library")
    id("maven-publish")
    id("fr.brouillard.oss.gradle.jgitver")
//    id("application")
}

//version = "0.1"
group = "org.essexstreet"

repositories {
    mavenCentral()
}

dependencies {
//    ksp("io.micronaut.validation:micronaut-validation-processor")
//    ksp("io.micronaut:micronaut-http-validation")
//    ksp("io.micronaut.serde:micronaut-serde-processor")
//    implementation("io.micronaut:micronaut-http-client-jdk")
////    implementation("io.micronaut.serde:micronaut-serde")
//    implementation("io.micronaut.aws:micronaut-aws-apigateway")
//    implementation("io.micronaut.aws:micronaut-aws-lambda-events-serde")
//    implementation("io.micronaut.validation:micronaut-validation")
//    implementation("io.micronaut.serde:micronaut-serde-jackson")
//    api("ch.qos.logback:logback-classic")
    api("net.logstash.logback:logstash-logback-encoder:7.4")
}

//application {
//    mainClass = "com.example.Application"
//}

java {
    sourceCompatibility = JavaVersion.toVersion("17")
    targetCompatibility = JavaVersion.toVersion("17")
}


jgitver {
    strategy = Strategies.MAVEN
}

//micronaut {
//    runtime("lambda_provided")
//    testRuntime("junit5")
//    nativeLambda {
//        lambdaRuntimeClassName = "io.micronaut.function.aws.runtime.MicronautLambdaRuntime"
//    }
//    processing {
//        incremental(true)
//        annotations("com.example.*")
//    }
//    aot {
//        // Please review carefully the optimizations enabled below
//        // Check https://micronaut-projects.github.io/micronaut-aot/latest/guide/ for more details
//        optimizeServiceLoading = false
//        convertYamlToJava = false
//        precomputeOperations = true
//        cacheEnvironment = true
//        optimizeClassLoading = true
//        deduceEnvironment = true
//        optimizeNetty = true
//        replaceLogbackXml = true
//    }
//}
//
//graalvmNative {
//    toolchainDetection.set(false)
////    binaries {
////        main {
////
////            imageName.set('mn-graalvm-application')
////        }
////    }
////    binaries {
////        main {
////            imageName.set('mn-graalvm-application')
//////            buildArgs.add('--verbose')
////        }
////    }
//}

//tasks.named<io.micronaut.gradle.docker.NativeImageDockerfile>("dockerfileNative") {
//    baseImage = "amazonlinux:2023"
//    jdkVersion = "21"
//    args(
//        "-XX:MaximumHeapSizePercent=80",
//        "-Dio.netty.allocator.numDirectArenas=0",
//        "-Dio.netty.noPreferDirect=true"
//    )
//}


