import java.io.File

pluginManagement {
    includeBuild("../build-logic")
}

plugins {
    id("multimodule")
}

fun includeSubs(base: String, path: String = base, vararg subs: String) {
    subs.forEach {
        include(":$base-$it")
        project(":$base-$it").projectDir = File("$path/$it")
    }
}

listOf(
 	"lexi", "neat", "kash-api", "kash-client", "geo-api", "geo-client", "kronecker", "symphony", 
 	"epsilon-api", "krono-core", "krono-client", "hormone", "identifier-api", "kommerce",
 	"kollections", "koncurrent", "kommander", "cabinet-api","epsilon-client",
).forEach { includeBuild("../$it") }

rootProject.name = "raven-client"

// <BitframeUtils>
includeSubs(base = "raven", path = ".", "api", "mock", "smtp")
