package pw.kmp.projectether.validation

data class ValidationResult(
    val isValid: Boolean,
    val messages: List<ValidationMessage>,
)
