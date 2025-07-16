package pw.kmp.projectether.domain.usecase.login

import pw.kmp.projectether.GameClient
import pw.kmp.projectether.data.model.session.Session

interface LoginUseCase {
    suspend operator fun invoke(username: String, password: String): Result<Session>
}

class DefaultLoginUseCase(
    private val gameClient: GameClient,
) : LoginUseCase {
    override suspend operator fun invoke(username: String, password: String): Result<Session> {
        return try {
            gameClient.connect(username, password)
            Result.success(Session("test"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}