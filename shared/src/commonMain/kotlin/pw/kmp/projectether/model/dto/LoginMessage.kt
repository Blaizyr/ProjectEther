package pw.kmp.projectether.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginMessage(val type: String = "login", val username: String)