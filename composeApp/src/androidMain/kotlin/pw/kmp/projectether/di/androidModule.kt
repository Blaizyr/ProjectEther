package pw.kmp.projectether.di

import org.koin.dsl.module
import pw.kmp.projectether.AndroidGameLauncher
import pw.kmp.projectether.GODOT_CLIENT_PACKAGE_NAME
import pw.kmp.projectether.GODOT_CLIENT_PATH
import pw.kmp.projectether.component.GameLauncher

val androidModule = module {
    single<GameLauncher> {
        AndroidGameLauncher(
            get(),
//            GODOT_CLIENT_PATH,
//            GODOT_CLIENT_PACKAGE_NAME
        )
    }
}
