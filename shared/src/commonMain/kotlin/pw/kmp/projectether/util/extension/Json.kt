package pw.kmp.projectether.util.extension

import kotlinx.serialization.json.Json

val jsonConfig = Json {
    classDiscriminator = "type"
    ignoreUnknownKeys = true
}

inline fun <reified T> T.encodeWithDiscriminator(): String {
    return jsonConfig.encodeToString(this)
}

inline fun <reified T> String.decodeWithDiscriminator(): T {
    return jsonConfig.decodeFromString(this)
}