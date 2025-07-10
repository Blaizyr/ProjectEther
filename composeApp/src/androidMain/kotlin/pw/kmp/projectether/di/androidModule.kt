package pw.kmp.projectether.di

import org.koin.dsl.module
import pw.kmp.projectether.godotClient.AndroidGodotClientLauncher
import pw.kmp.projectether.GodotClientLauncher

val androidModule = module {
    single<GodotClientLauncher> {
        AndroidGodotClientLauncher()
    }
}
