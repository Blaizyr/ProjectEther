package pw.kmp.projectether

import androidx.compose.runtime.Composable
import pw.kmp.projectether.godot.JvmGodotClient

@Composable
fun DesktopGodotContent(
    godotClient: JvmGodotClient
) {
    godotClient.shutdown()
}
