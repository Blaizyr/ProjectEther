package pw.kmp.projectether.validation.fields

import pw.kmp.projectether.component.login.LoginFormField
import pw.kmp.projectether.validation.ValidationMessage

fun LoginFormField.rule(
    condition: Boolean,
    error: String,
    success: String,
): ValidationMessage =
    if (condition) ValidationMessage("$label: $error", ValidationMessage.Type.ERROR)
    else ValidationMessage("$label: $success", ValidationMessage.Type.SUCCESS)


fun LoginFormField.ruleWarn(
    errorCondition: Boolean,
    warningCondition: Boolean,
    error: String,
    warning: String,
    success: String,
): ValidationMessage =
    when {
        warningCondition -> ValidationMessage(warning, ValidationMessage.Type.WARNING)
        errorCondition -> ValidationMessage(error, ValidationMessage.Type.ERROR)
        else -> ValidationMessage(success, ValidationMessage.Type.SUCCESS)
    }
