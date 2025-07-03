package pw.kmp.projectether.di

import org.koin.dsl.module
import pw.kmp.projectether.AndroidGameLauncher
import pw.kmp.projectether.component.GameLauncher

val androidModule = module {
    single<GameLauncher> { AndroidGameLauncher(get()) }
}
