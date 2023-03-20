package at.xirado.githook.action.handler

import at.xirado.githook.action.ActionStep
import at.xirado.githook.action.CommandAction
import at.xirado.githook.io.StreamReader
import at.xirado.githook.util.shell
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import org.athena.util.getLog
import org.athena.util.newCoroutineScope
import java.io.File

private val log = getLog<CommandActionHandler>()
private val scope = newCoroutineScope<CommandActionHandler>()

object CommandActionHandler : ActionHandler {
    override suspend fun run(step: ActionStep): ActionResult {
        val output = mutableListOf<String>()
        val action = step.current as CommandAction
        val workingDir = action.workingDirectory?.let { File(it) }

        val command = action.command

        val shellCmd = shell + command.exec

        val process = withContext(Dispatchers.IO) {
            ProcessBuilder(shellCmd).apply {
                if (workingDir != null)
                    directory(workingDir)
            }.start()
        }

        val linesCombined = mutableListOf<String>()

        val stdout = StreamReader(process.inputStream) {
            synchronized(linesCombined) {
                val trimmed = it.trim()
                linesCombined += trimmed
                log.info("|> $trimmed")
            }
        }

        val stderr = StreamReader(process.errorStream) {
            synchronized(linesCombined) {
                val trimmed = it.trim()
                linesCombined += trimmed
                log.info("|> $trimmed")
            }
        }

        val (stdoutStr, stdErrStr) = awaitAll(
            scope.async { stdout.read() },
            scope.async { stderr.read() }
        )

        stdout.close()
        stderr.close()

        output += linesCombined.joinToString("\n").trim()

        val exitCode = process.exitValue()

        val successOn = command.exitCode ?: emptyList()

        if (exitCode !in successOn)
            throw RuntimeException("Command \"${command.exec}\" failed! (Exit code: $exitCode)")

        log.info("Command \"${command.exec}\" finished with exit code ${process.exitValue()}")

        return ActionResult(output.joinToString("\n\n"))
    }
}