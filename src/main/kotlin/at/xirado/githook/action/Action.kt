package at.xirado.githook.action

import kotlinx.serialization.Serializable

@Serializable
sealed class Action {
    abstract val name: String
    abstract val children: List<Action>?
}