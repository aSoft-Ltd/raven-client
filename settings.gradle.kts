pluginManagement {
    enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
    repositories {
        mavenCentral()
        google()
        gradlePluginPortal()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.namespace == "com.android") {
                useModule("com.android.tools.build:gradle:${requested.version}")
            }
        }
    }

    dependencyResolutionManagement {
        versionCatalogs {
            file("gradle/versions").listFiles().map {
                it.nameWithoutExtension to it.absolutePath
            }.forEach { (name, path) ->
                create(name) { from(files(path)) }
            }
        }
    }
}

fun includeSubs(base: String, path: String = base, vararg subs: String) {
    subs.forEach {
        include(":$base-$it")
        project(":$base-$it").projectDir = File("$path/$it")
    }
}

listOf(
 	"lexi", "neat", "kash-api", "kash-client", "geo-api", "geo-client", "kronecker", "symphony", 
 	"epsilon-api", "krono-core", "krono-client", "hormone", "identifier-client", "kommerce",
 	"kollections", "koncurrent", "kommander", "cabinet-api","epsilon-client",
).forEach { includeBuild("../$it") }

rootProject.name = "raven-client"

// <BitframeUtils>
includeSubs(base = "raven", path = ".", "api", "mock", "smtp")
