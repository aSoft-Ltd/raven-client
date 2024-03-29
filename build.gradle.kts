import com.vanniktech.maven.publish.MavenPublishBaseExtension
import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(kotlinz.plugins.multiplatform) apply false
    alias(kotlinz.plugins.serialization) apply false
    alias(asoft.plugins.library) apply false
    alias(vanniktech.plugins.maven.publish) apply false
}

repositories {
    publicRepos()
}

val v = libs.versions.asoft.get()

group = "tz.co.asoft"
version = v

allprojects {
    group = "tz.co.asoft"
    version = v
}