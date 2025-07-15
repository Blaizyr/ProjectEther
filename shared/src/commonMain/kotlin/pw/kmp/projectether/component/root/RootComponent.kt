package pw.kmp.projectether.component.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.replaceCurrent
import com.arkivanov.decompose.value.Value
import org.koin.core.component.KoinComponent
import pw.kmp.projectether.GameClient
import pw.kmp.projectether.component.game.DefaultGameComponent
import pw.kmp.projectether.component.login.DefaultLoginComponent

class RootComponent(
    componentContext: ComponentContext,
) : ComponentContext by componentContext, KoinComponent {

    private val navigation = StackNavigation<Child>()

    val childStack: Value<ChildStack<Child, Any>> = childStack(
        source = navigation,
        serializer = Child.serializer(),
        initialConfiguration = Child.Login,
        handleBackButton = true,
        childFactory = ::createChild,
    )

    private fun createChild(child: Child, context: ComponentContext): Any {
        val gameClient: GameClient = getKoin().get()

        return when (child) {
            is Child.Login -> DefaultLoginComponent(
                onLoginSuccess = { sessionId ->
                    navigation.replaceCurrent(Child.Game(sessionId))
                },
                gameClient = gameClient,
                componentContext = context
            )

            is Child.Game -> DefaultGameComponent(
                gameClient = gameClient,
                componentContext = context
            )
        }
    }
}
