package net.d7z.vfs.sys

import net.d7z.vfs.VirtualFile

/**
 * 对象文件系统
 *
 *
 *
 * @property schema String 文件系统类型，将作为唯一标识符
 */
interface FileSystem {
    val schema: String

    fun open(path: String): Result<VirtualFile>

    fun listFiles(path: String, filter: (VirtualFile) -> Boolean = { true }): List<VirtualFile>
}
