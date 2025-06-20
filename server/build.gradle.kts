plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    application
}

group = "pw.kmp.projectether"
version = "1.0.0"
application {
    mainClass.set("pw.kmp.projectether.ApplicationKt")
    
    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {
    implementation(projects.shared)
    implementation(libs.logback)
    implementation(project.dependencies.platform(libs.koin.bom))
    implementation(libs.bundles.koin.server)
    implementation(libs.bundles.ktor.server)
    testImplementation(libs.kotlin.testJunit)
}