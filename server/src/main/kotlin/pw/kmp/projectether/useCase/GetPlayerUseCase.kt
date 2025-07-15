package pw.kmp.projectether.useCase

import pw.kmp.projectether.data.model.player.Player
import pw.kmp.projectether.repository.PlayerRepository

class GetPlayerUseCase(private val playerRepository: PlayerRepository) {

    fun getPlayerByUsername(username: String): Player? = playerRepository.getByUsername(username)

    fun getPlayerById(id: Long): Player? = playerRepository.getById(id)
}