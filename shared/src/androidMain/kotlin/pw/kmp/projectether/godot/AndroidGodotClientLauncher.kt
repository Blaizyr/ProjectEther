package pw.kmp.projectether.godot

import org.godotengine.godot.GodotFragment
import pw.kmp.projectether.getPlatform

class AndroidGodotClientLauncher : GodotClientLauncher {
    override fun launchGodotClient(): GodotClient {
        return AndroidGodotClient(
            godotClientFragment = GodotFragment(),
            platform = getPlatform()
        )
    }
}
