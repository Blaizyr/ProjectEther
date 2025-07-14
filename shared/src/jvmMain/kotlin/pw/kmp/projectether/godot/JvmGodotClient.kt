package pw.kmp.projectether.godot

import pw.kmp.projectether.Platform

class JvmGodotClient(
    private val jvmGodotClientProcess: Process,
    override val platform: Platform
) : GodotClient {

    override fun shutdown() = jvmGodotClientProcess.destroy()
}