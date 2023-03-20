package at.xirado.githook.action.handler

import at.xirado.githook.action.ActionStep

interface ActionHandler {
    suspend fun run(step: ActionStep) : ActionResult?
}

data class ActionResult(val output: String)