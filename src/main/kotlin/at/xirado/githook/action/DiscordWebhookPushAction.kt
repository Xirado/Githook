package at.xirado.githook.action

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("send_webhook")
data class DiscordWebhookAction(
    override val name: String,
    override val children: List<Action>? = emptyList(),

    val url: String,
    val mentionRole: Long?,
    val mentionUser: Long?,
) : Action()
