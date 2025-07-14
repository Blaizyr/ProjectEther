package pw.kmp.projectether.component

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import pw.kmp.projectether.godot.GodotClient
import pw.kmp.projectether.godot.launchGodotClient

data class GameClientState(
    val loggedIn: Boolean = false,
    val godotClient: GodotClient? = null
)

class GameComponent(
//    private val gameClient: GameClient,
    componentContext: ComponentContext
) : ComponentContext by componentContext, InstanceKeeper.Instance {

    private val _uiState = MutableStateFlow(GameClientState())
    val uiState = _uiState.asStateFlow()

    private val godotScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    init {
        launchGodotClient()
    }

    private fun launchGodotClient() {
        godotScope.launch {
            _uiState.update {
                it.copy(godotClient = launchGodotClient())
            }
        }
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