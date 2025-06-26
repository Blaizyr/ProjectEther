package pw.kmp.projectether.di

import org.koin.dsl.module
import pw.kmp.projectether.GameSessionManager

val serverModule = module {
    single { GameSessionManager() }
}