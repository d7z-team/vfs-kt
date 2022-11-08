package net.d7z.vfs.sys

import net.d7z.vfs.std.VFile

/**
 * 对象文件系统
 *
 *
 *
 * @property schema String 文件系统类型，将作为唯一标识符
 */
interface VFileSystem {
    /**
     * 文件系统标识符
     */
    val schema: String

    /**
     * 根据打开一个文件对象
     * @param path String
     * @return Result<VFile>
     */
    fun open(path: String): Result<VFile>

    /**
     * 销毁文件系统
     */
    fun close()
}
