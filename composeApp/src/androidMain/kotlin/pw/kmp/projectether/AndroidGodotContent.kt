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
            val frameLayout = FrameLayout(context).apply {
                id = View.generateViewId()
            }
            frameLayout.post {
                val activity = context as? FragmentActivity
                    ?: error("AndroidGodotContent requires the context to be a FragmentActivity.")

                if (activity.supportFragmentManager.findFragmentById(frameLayout.id) == null) {
                    activity.supportFragmentManager.beginTransaction()
                        .replace(frameLayout.id, godotClient.godotClientFragment)
                        .commitNowAllowingStateLoss()
                }
            }
            frameLayout
        }
    )
}
