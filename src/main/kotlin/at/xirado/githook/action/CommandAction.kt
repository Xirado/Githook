package at.xirado.githook.action

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("command")
data class CommandAction(
    override val name: String,
    override val children: List<Action>? = emptyList(),

    @SerialName("working_directory")
    val workingDirectory: String? = null,
    val command: Command,
) : Action()

@Serializable
data class Command(
    val exec: String,
    @SerialName("exit_code")
    val exitCode: List<Int>? = listOf(0),
)