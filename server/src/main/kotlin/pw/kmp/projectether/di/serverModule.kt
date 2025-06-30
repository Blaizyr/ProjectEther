package pw.kmp.projectether.di

import org.koin.dsl.module
import pw.kmp.projectether.GameSessionManager
import pw.kmp.projectether.repository.PlayerRepository
import pw.kmp.projectether.socket.WebSocketController
import pw.kmp.projectether.useCase.CreatePlayerUseCase
import pw.kmp.projectether.useCase.GetPlayerUseCase

val serverModule = module {
    single { WebSocketController(get(), get(), get()) }
    single { GameSessionManager() }
    single { PlayerRepository() }
    single { GetPlayerUseCase(get()) }
    single { CreatePlayerUseCase(get()) }
}