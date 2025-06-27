package pw.kmp.projectether.useCase

import pw.kmp.projectether.model.player.Player
import pw.kmp.projectether.repository.PlayerRepository

class CreatePlayerUseCase(private val playerRepository: PlayerRepository) {

    fun createPlayer(username: String): Player = playerRepository.add(username)
}