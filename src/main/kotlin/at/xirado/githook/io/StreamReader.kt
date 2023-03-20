package at.xirado.githook.io

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream

class StreamReader(val stream: InputStream, val onLine: (String) -> Unit) : AutoCloseable {
    suspend fun read(): String {
        val lines = mutableListOf<String>()

        val current = StringBuilder()
        while (true) {
            val c = withContext(Dispatchers.IO) { stream.read() }

            if (c == -1) {
                if (current.isNotEmpty()) {
                    current.toString().let {
                        onLine(it)
                        lines += it
                    }
                }
                break;
            }

            val char = c.toChar()

            if (char == '\n') {
                current.toString().let {
                    onLine(it)
                    lines += it
                }

                current.clear()
            }

            current.append(char)

        }

        return lines.joinToString()
    }

    override fun close() {
        stream.close()
    }
}