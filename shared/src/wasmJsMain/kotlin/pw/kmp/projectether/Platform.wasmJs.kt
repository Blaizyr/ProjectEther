package pw.kmp.projectether

class WasmPlatform: Platform {
    override val name: String = "Web with Kotlin/Wasm"
    override val isTouch: Boolean = false
}

actual fun getPlatform(): Platform = WasmPlatform()