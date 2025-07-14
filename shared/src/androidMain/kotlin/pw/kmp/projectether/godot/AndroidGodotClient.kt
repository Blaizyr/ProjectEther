package pw.kmp.projectether.godot

import org.godotengine.godot.GodotFragment
import pw.kmp.projectether.Platform

class AndroidGodotClient(
    val godotClientFragment: GodotFragment,
    override val platform: Platform,
) : GodotClient {
    override fun shutdown() {
        godotClientFragment.onDestroy()
    }
}