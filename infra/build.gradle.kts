plugins {
    id("application")
    id("java")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("io.micronaut.platform:micronaut-platform:4.2.1"))
    implementation("io.micronaut.starter:micronaut-starter-aws-cdk:4.1.6") {
        exclude(group = "software.amazon.awscdk", module = "aws-cdk-lib")
    }
    implementation("software.amazon.awscdk:aws-cdk-lib:2.114.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-engine")
}

application {
    mainClass.set("org.essexstreet.Main")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

