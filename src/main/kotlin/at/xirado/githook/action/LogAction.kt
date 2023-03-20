package at.xirado.githook.action

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("log")
data class LogAction(
    override val name: String,
    override val children: List<Action>? = emptyList(),
) : Action()