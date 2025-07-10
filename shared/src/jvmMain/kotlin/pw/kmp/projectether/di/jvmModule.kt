package pw.kmp.projectether.di

import org.koin.dsl.module
import pw.kmp.projectether.godot.GodotClientLauncher
import pw.kmp.projectether.godot.JvmGodotClientLauncher

val jvmModule = module {
    single<GodotClientLauncher> { JvmGodotClientLauncher() }
}