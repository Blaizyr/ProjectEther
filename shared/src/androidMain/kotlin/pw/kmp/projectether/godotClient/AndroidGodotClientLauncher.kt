package pw.kmp.projectether.godotClient

import org.godotengine.godot.GodotFragment
import pw.kmp.projectether.GodotClient
import pw.kmp.projectether.GodotClientLauncher
import pw.kmp.projectether.getPlatform

class AndroidGodotClientLauncher : GodotClientLauncher {

    override fun launchGodotClient(): GodotClient {
        return AndroidGodotClient(
            godotClientFragment = GodotFragment(),
            platform = getPlatform()
        )
    }
}
