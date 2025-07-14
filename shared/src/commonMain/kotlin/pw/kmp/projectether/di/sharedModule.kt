package pw.kmp.projectether.di

import org.koin.dsl.module
import pw.kmp.projectether.GameClient
import pw.kmp.projectether.createHttpClient

val sharedModule = module {
    single { GameClient(createHttpClient()) }
}