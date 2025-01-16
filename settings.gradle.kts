import org.gradle.internal.impldep.org.bouncycastle.util.Properties

rootProject.name = "infinityindex"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")



pluginManagement {
    val localProperties = File(rootDir, "local.properties").inputStream().use {
        java.util.Properties().apply { load(it) }
    }
    val pat = localProperties.getValue("GH_PAT") as String

    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/edna-aa/sqldelight")
            credentials {
                username = "wongislandd"
                password = pat
            }
            // Restrict this repository to specific versions containing "-wasm"
            content {
                includeGroup("app.cash.sqldelight") // Restrict to the group
                includeGroup("app.cash.paging") // Restrict to the group
                includeVersionByRegex("app.cash.sqldelight", ".*", ".*-wasm.*") // Match any artifact in the group with versions containing "-wasm"
                includeVersionByRegex("app.cash.paging", ".*", ".*-wasm.*") // Match any artifact in the group with versions containing "-wasm"
            }
        }
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    val localProperties = File(rootDir, "local.properties").inputStream().use {
        java.util.Properties().apply { load(it) }
    }
    val pat = localProperties.getValue("GH_PAT") as String
    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/edna-aa/sqldelight")
            credentials {
                username = "wongislandd"
                password = pat
            }
            // Restrict this repository to specific versions containing "-wasm"
            content {
                includeGroup("app.cash.sqldelight") // Restrict to the group
                includeGroup("app.cash.paging") // Restrict to the group
                includeVersionByRegex("app.cash.sqldelight", ".*", ".*-wasm.*") // Match any artifact in the group with versions containing "-wasm"
                includeVersionByRegex("app.cash.paging", ".*", ".*-wasm.*") // Match any artifact in the group with versions containing "-wasm"
            }
        }
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

include(":composeApp")
include(":server")
include(":shared")