package pw.kmp.projectether.godot

import android.os.Bundle
import org.godotengine.godot.GodotFragment
import pw.kmp.projectether.getPlatform

actual fun launchGodotClient(): GodotClient {
    val fragment = GodotFragment().apply {
        arguments = Bundle().apply {
            putString("main_pack", "projectether-debug.pck")
        }
    }
    return AndroidGodotClient(
        godotClientFragment = fragment,
        platform = getPlatform()
    )
}
