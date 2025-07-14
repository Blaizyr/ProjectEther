package pw.kmp.projectether.godot

import pw.kmp.projectether.Platform

class WasmGodotClient(
    // TODO("Implement GodotEngine param and Embed pattern for Wasm") #10
    override val platform: Platform
) : GodotClient {
    override fun shutdown() {
        // TODO("Implement wasm godot shutdown") #12
    }
}