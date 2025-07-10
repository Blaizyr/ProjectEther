package pw.kmp.projectether

import androidx.compose.runtime.Composable
import pw.kmp.projectether.godot.GodotClient

@Composable
expect fun GodotClient.RenderContent()