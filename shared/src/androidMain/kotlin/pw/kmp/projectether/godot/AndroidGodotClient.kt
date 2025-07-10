package pw.kmp.projectether.godot

import org.godotengine.godot.GodotFragment
import pw.kmp.projectether.Platform

class AndroidGodotClient(
    godotClientFragment: GodotFragment,
    override val platform: Platform,
) : GodotClient