package at.xirado.githook.action.handler

import at.xirado.githook.action.ActionStep

object LogActionHandler : ActionHandler {
    override suspend fun run(step: ActionStep): ActionResult {
        println(step.parent?.result?.output)
        println("Test")
        return ActionResult("")
    }
}