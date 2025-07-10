package pw.kmp.projectether

import androidx.compose.runtime.Composable
import pw.kmp.projectether.godot.AndroidGodotClient

@Composable
fun AndroidGodotContent(
    godotClient: AndroidGodotClient,
) {
    godotClient.RenderContent()
}
