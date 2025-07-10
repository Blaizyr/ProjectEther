package pw.kmp.projectether.godot

import pw.kmp.projectether.getPlatform

class WasmGodotClientLauncher : GodotClientLauncher {
    override fun launchGodotClient(): GodotClient {
        return WasmGodotClient(getPlatform())
    }
}