package pw.kmp.projectether.validation

data class ValidationMessage(
    val message: String,
    val type: Type,
) {
    enum class Type {
        ERROR,
        WARNING,
        SUCCESS,
    }
}
