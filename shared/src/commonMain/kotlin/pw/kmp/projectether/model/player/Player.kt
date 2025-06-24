package pw.kmp.projectether.model.player

data class Player(
    val id: Long,
    val name: String,
    val hero: Hero?,
)

data class Hero(
    val id: Long,
    val name: String,
    val description: String,
    val model: String,
)