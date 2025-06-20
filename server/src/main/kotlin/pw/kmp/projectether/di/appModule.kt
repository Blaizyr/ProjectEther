package pw.kmp.projectether.di

import org.koin.dsl.module
import pw.kmp.projectether.Greeting

val appModule = module {
    single { Greeting() }
}