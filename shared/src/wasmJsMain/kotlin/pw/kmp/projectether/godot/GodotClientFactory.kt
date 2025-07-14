package pw.kmp.projectether.godot

import pw.kmp.projectether.getPlatform

actual fun launchGodotClient(): GodotClient {
    return WasmGodotClient(getPlatform())
}
