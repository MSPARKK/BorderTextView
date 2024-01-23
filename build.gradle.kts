// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.4.2" apply false
    id("org.jetbrains.kotlin.android") version "1.8.0" apply false
    id("com.android.library") version "7.4.2" apply false
    id("io.github.gradle-nexus.publish-plugin") version "1.1.0" apply true
}

buildscript {
    dependencies {
        classpath("com.github.dcendents:android-maven-gradle-plugin:2.1")
        classpath("io.github.gradle-nexus:publish-plugin:1.1.0")
    }
}

apply(from = "${rootDir}/scripts/publish-maven.gradle")
apply(from = "publish.gradle")
