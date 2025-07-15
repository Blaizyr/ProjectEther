package pw.kmp.projectether.component.game

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pw.kmp.projectether.GameClient
import pw.kmp.projectether.godot.GodotClient
import pw.kmp.projectether.godot.launchGodotClient

data class GameClientState(
    val loggedIn: Boolean = false,
    val sessionId: String? = null,
)

interface GameComponent {
    val uiState : StateFlow<GameClientState>
    val godotClientState: StateFlow<GodotClient?>
    fun shutdownGodot()
    fun rebootGodot()
}

class DefaultGameComponent(
    private val gameClient: GameClient,
    componentContext: ComponentContext
) : GameComponent, ComponentContext by componentContext, InstanceKeeper.Instance {

    private val _uiState = MutableStateFlow(GameClientState())
    override val uiState = _uiState.asStateFlow()

    private val _godotClientState = MutableStateFlow<GodotClient?>(null)
    override val godotClientState = _godotClientState.asStateFlow()

    private val godotScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    init {
        initializeGodotClient()
    }

    override fun shutdownGodot() = shutdownGodotClient()

    override fun rebootGodot() {
        shutdownGodotClient()
        initializeGodotClient()
    }

    private fun initializeGodotClient() {
        godotScope.launch {
            _godotClientState.update {
                launchGodotClient()
            }
        }
    }

    private fun shutdownGodotClient() {
        _godotClientState.value?.shutdown()
        _godotClientState.value = null
    }
    /* TODO("implement events of navigation") #8
        init {
            godotScope.launch { gameClient.events.collect { event ->
                when (event) {
                    is GameClientEvent.LoggedIn -> {
                        _uiState.update { it.copy(loggedIn = true) }
                        gameLauncher.launchGodotClient()

                    }
                }
            } }
        }
    */
}