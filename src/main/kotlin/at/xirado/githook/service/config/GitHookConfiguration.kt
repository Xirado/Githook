package at.xirado.githook.service.config

import at.xirado.githook.action.Action
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import org.athena.service.ConfigurationProvider

@Serializable
data class GitHookConfiguration(
    val rest: RestConfiguration,
    val trigger: List<Trigger> = emptyList(),
)

object GitHookConfigurationProvider : ConfigurationProvider<GitHookConfiguration> {
    override val prefix: String = "githook"
    override val serializer: KSerializer<GitHookConfiguration>
        get() = GitHookConfiguration.serializer()
}

@Serializable
data class Trigger(
    val repository: String,
    val branch: List<String>? = emptyList(),
    val action: List<Action>? = emptyList(),
)

@Serializable
data class RestConfiguration(
    val host: String = "0.0.0.0",
    val port: Int = 7245,
    val secret: String
)