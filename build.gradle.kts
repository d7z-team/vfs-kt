@file:Suppress("UNUSED_VARIABLE")

plugins {
    kotlin("multiplatform") version "1.7.20"
    `maven-publish`
}

group = rootProject.property("group")!!
version = rootProject.property("version")!!

repositories {
    mavenCentral()
    maven { setUrl("https://repos.d7z.net/maven") }
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    js(BOTH) {
        browser {
            commonWebpackConfig() {
                cssSupport.enabled = true
            }
        }
    }
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api("com.github.d7z-team.kmm-utils:kmm-utils:0.0.1-SNAPSHOT") // library shared for all source sets
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting
        val jvmTest by getting
        val jsMain by getting
        val jsTest by getting
        val nativeMain by getting
        val nativeTest by getting
    }
}
publishing {
    repositories {
        mavenLocal()
        project.findProperty("m2.url") ?: System.getenv("MAVEN_REPO_URL")?.toString()?.let {
            maven {
                name = "Remote"
                url = project.uri(it)
                credentials {
                    username = (project.findProperty("m2.account") ?: System.getenv("MAVEN_ACCOUNT"))?.toString()
                    password = (project.findProperty("m2.password") ?: System.getenv("MAVEN_PASSWORD"))?.toString()
                }
            }
        }
    }
}
