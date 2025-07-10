package pw.kmp.projectether

import android.view.View
import android.widget.FrameLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentActivity
import pw.kmp.projectether.godot.AndroidGodotClient

@Composable
fun AndroidGodotContent(
    godotClient: AndroidGodotClient,
) {
    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = { context ->
            FrameLayout(context).apply {
                id = View.generateViewId()

                val activity = context as? FragmentActivity
                    ?: error("Context is not FragmentActivity")

                activity.supportFragmentManager.beginTransaction()
                    .replace(this.id, godotClient.godotClientFragment)
                    .commitNowAllowingStateLoss()
            }
        }
    )
}
