package pw.kmp.projectether

import androidx.compose.runtime.Composable
import pw.kmp.projectether.godot.AndroidGodotClient
import pw.kmp.projectether.godot.GodotClient

@Composable
actual fun GodotClient.Content() {
    val client = this as AndroidGodotClient
    AndroidGodotContent(client)
}