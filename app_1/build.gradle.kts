plugins {
  id("com.github.johnrengelman.shadow") version "8.1.1"

  id("io.micronaut.application") version "4.4.0"
  id("io.micronaut.aot") version "4.4.1"

//    alias(mn.

//    alias(mn.)
}

version = "0.1"
group = "org.essexstreet"

repositories {
  mavenCentral()
}

dependencies {
//    annotationProcessor(mn.micr)
//    compileOnly(mn.micronaut.spring.boot.annotation)
  annotationProcessor(mn.micronaut.validation.processor)
//    annotationProcessor("io.micronaut.validation:micronaut-validation-processor")
  annotationProcessor("io.micronaut:micronaut-http-validation")
  implementation("io.micronaut:micronaut-http-client-jdk")
  implementation("io.micronaut.aws:micronaut-aws-apigateway")
  implementation("io.micronaut.aws:micronaut-aws-lambda-events-serde")
  implementation("io.micronaut.validation:micronaut-validation")
  implementation("io.micronaut:micronaut-jackson-databind")
  implementation(project(":lib"))
  implementation("ch.qos.logback:logback-classic")
  implementation("net.logstash.logback:logstash-logback-encoder:7.4")
}


application {
  mainClass = "org.essexstreet.Application"
}
java {
  sourceCompatibility = JavaVersion.toVersion("17")
  targetCompatibility = JavaVersion.toVersion("17")
}


graalvmNative {
  binaries {
    all {

    }
    named("main") {
      buildArgs.add("--verbose")
//      buildArgs.add-H:+PrintClassInitialization
      buildArgs("-H:ReflectionConfigurationFiles=/home/app/resources/META-INF/native-image/logback-config.json")
      buildArgs.add("--enable-all-security-services")
      buildArgs.add("--report-unsupported-elements-at-runtime")
      buildArgs.add("--initialize-at-build-time=io.micronaut.jackson.modules.ParameterNamesModule,ch.qos.logback,net.logstash.logback,net.logstash.logback.encoder.LoggingEventCompositeJsonEncoder,org.slf4j.LoggerFactory")
    }
  }
}

graalvmNative.toolchainDetection = false

micronaut {
  runtime("lambda_provided")
  testRuntime("junit5")
  nativeLambda {
    lambdaRuntimeClassName = "io.micronaut.function.aws.runtime.MicronautLambdaRuntime"
  }
  processing {
    incremental(true)
    annotations("org.essexstreet.*")
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
    replaceLogbackXml = false
  }
}


tasks.named<io.micronaut.gradle.docker.NativeImageDockerfile>("dockerfileNative") {
  baseImage = "amazonlinux:2023"
  jdkVersion = "17"
  args(
    "-XX:MaximumHeapSizePercent=80", "-Dio.netty.allocator.numDirectArenas=0", "-Dio.netty.noPreferDirect=true"
  )
}


