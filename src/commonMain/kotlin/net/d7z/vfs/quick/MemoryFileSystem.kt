package net.d7z.vfs.quick

import net.d7z.vfs.io.BufReader
import net.d7z.vfs.io.BufWriter
import net.d7z.vfs.std.VFile
import net.d7z.vfs.sys.VFileSystem

class MemoryFileSystem : VFileSystem {
    override val schema = "memory"

    interface InternalObject {
        fun reader(): BufReader
        fun writer(create: Boolean, append: Boolean): BufWriter
    }

    private val fileTree = HashMap<String, InternalObject>()

    override fun open(path: String): Result<VFile> {
        TODO("Not yet implemented")
    }

    override fun close() {
        TODO("Not yet implemented")
    }
}
