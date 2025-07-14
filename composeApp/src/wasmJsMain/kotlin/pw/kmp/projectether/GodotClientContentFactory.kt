package pw.kmp.projectether

import androidx.compose.runtime.Composable
import pw.kmp.projectether.godot.GodotClient
import pw.kmp.projectether.godot.WasmGodotClient

@Composable
actual fun GodotClient.Content() {
    val client = this as WasmGodotClient
    WebGodotContent(client)
}
