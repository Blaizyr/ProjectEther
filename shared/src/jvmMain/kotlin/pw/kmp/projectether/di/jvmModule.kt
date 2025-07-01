package pw.kmp.projectether.di

import org.koin.dsl.module
import pw.kmp.projectether.JvmGameLauncher
import pw.kmp.projectether.component.GameLauncher

val jvmModule = module {
    single<GameLauncher> { JvmGameLauncher() }
}