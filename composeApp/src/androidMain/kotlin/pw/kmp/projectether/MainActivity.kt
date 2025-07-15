package pw.kmp.projectether

import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.fragment.app.FragmentActivity
import com.arkivanov.decompose.DefaultComponentContext
import pw.kmp.projectether.component.root.RootComponent

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        requestedOrientation = SCREEN_ORIENTATION_LANDSCAPE
        val rootComponent = RootComponent(
            componentContext = DefaultComponentContext(lifecycle = lifecycle),
        )
        setContent {
            App(rootComponent)
        }
    }
}
