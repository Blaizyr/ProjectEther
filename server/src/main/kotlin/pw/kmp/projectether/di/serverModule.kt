package pw.kmp.projectether.di

import org.koin.dsl.module
import pw.kmp.projectether.GODOT_SERVER_PATH
import pw.kmp.projectether.repository.PlayerRepository
import pw.kmp.projectether.service.GameServerLauncher
import pw.kmp.projectether.service.GameSessionManager
import pw.kmp.projectether.socket.WebSocketController
import pw.kmp.projectether.useCase.CreatePlayerUseCase
import pw.kmp.projectether.useCase.GetPlayerUseCase

val serverModule = module {
    single { GameServerLauncher(GODOT_SERVER_PATH) }
    single { WebSocketController(get(), get(), get()) }
    single { GameSessionManager() }
    single { PlayerRepository() }
    single { GetPlayerUseCase(get()) }
    single { CreatePlayerUseCase(get()) }
}