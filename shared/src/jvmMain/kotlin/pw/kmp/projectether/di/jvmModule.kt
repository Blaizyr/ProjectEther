package pw.kmp.projectether.di

import org.koin.dsl.module
import pw.kmp.projectether.GodotClientLauncher
import pw.kmp.projectether.godotClient.JvmGodotClientLauncher

val jvmModule = module {
    single<GodotClientLauncher> { JvmGodotClientLauncher() }
}