package pw.kmp.projectether.di

import org.koin.dsl.module
import pw.kmp.projectether.godot.AndroidGodotClientLauncher
import pw.kmp.projectether.godot.GodotClientLauncher

val androidModule = module {
    single<GodotClientLauncher> {
        AndroidGodotClientLauncher()
    }
}
