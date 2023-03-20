package at.xirado.githook.util

import at.xirado.githook.action.Command
import okhttp3.internal.toImmutableList
import java.util.*

enum class OS {
    WINDOWS, LINUX, MAC, SOLARIS
}

val shell: List<String> by lazy {
    return@lazy when (os) {
        OS.WINDOWS -> listOf("cmd", "/c")
        OS.LINUX -> listOf("/usr/bin/sh", "-c")
        else -> throw IllegalStateException("Not supported")
    }
}

val os: OS by lazy {
    val operSys = System.getProperty("os.name").lowercase(Locale.getDefault())

    with(operSys) {
        when {
            contains("win") -> OS.WINDOWS
            contains("nix") || contains("nux") || contains("aix") -> OS.LINUX
            contains("mac") -> OS.MAC
            contains("sunos") -> OS.SOLARIS
            else -> throw RuntimeException("Unable to find OS")
        }
    }
}