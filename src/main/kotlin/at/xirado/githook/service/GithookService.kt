package at.xirado.githook.service

import at.xirado.githook.service.config.GitHookConfiguration
import at.xirado.githook.service.config.GitHookConfigurationProvider
import org.athena.service.ConfigurableService
import org.athena.service.ConfigurationProvider

interface GitHookConfigurable : ConfigurableService<GitHookConfigurationProvider, GitHookConfiguration> {
    override val provider: ConfigurationProvider<GitHookConfiguration>
        get() = GitHookConfigurationProvider
}