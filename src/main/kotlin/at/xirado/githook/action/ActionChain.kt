package at.xirado.githook.action

import at.xirado.githook.action.handler.ActionResult
import at.xirado.githook.action.handler.CommandActionHandler
import at.xirado.githook.action.handler.LogActionHandler
import at.xirado.githook.service.rest.RestService
import kotlinx.coroutines.launch
import org.athena.service.service
import org.athena.util.getLog

private val log = getLog<ActionChain>()

class ActionChain(private val root: Action?, private val parent: ActionMetadata?,  private val children: List<Action>) {
    val scope = service<RestService>().scope

    suspend fun begin() {
        var parentMeta: ActionMetadata? = null

        if (root != null) {
            val step = ActionStep(parent, root)

            val result = run(step)
                ?: throw IllegalStateException("No handler found for action $root!")
            parentMeta = ActionMetadata(root, result)
        }

        children.forEach { action ->
            val actionChain = ActionChain(action, parentMeta, action.children ?: emptyList())
            actionChain.begin()
        }
    }

    private suspend fun run(step: ActionStep): ActionResult? {
        return when (step.current) {
            is CommandAction -> CommandActionHandler.run(step)
            is LogAction -> LogActionHandler.run(step)
            else -> null
        }
    }
}

data class ActionStep(
    val parent: ActionMetadata?,
    val current: Action,
)

data class ActionMetadata(val action: Action, val result: ActionResult)