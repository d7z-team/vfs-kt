package net.d7z.vfs

import net.d7z.vfs.io.BufReader
import net.d7z.vfs.io.BufWriter
import org.d7z.utils.CommonOptional

/**
 * 虚拟对象
 */
interface VirtualFile {
    /**
     * 对象名称
     */
    val name: String

    /**
     * 对象路径
     */
    val path: String

    /**
     * 对象大小（如果为目录则永远等于 0）
     */
    val size: Long

    /**
     * 此路径是否存在对象
     *
     * 如果此路径下无文件
     *
     */
    val exists: Boolean

    /**
     * 此路径是否为文件对象
     */
    val isFile: Boolean

    /**
     * 此路径的父路径（当位于根路径下时，将无法得到父路径）
     */
    fun parent(): CommonOptional<VirtualFile>

    /**
     * 获取子路径对象
     *
     */
    fun child(path: String): Result<VirtualFile>

    /**
     * 获取对象的读取流
     *
     * 如果不是文件对象将导致出现错误
     *
     */
    fun reader(): Result<BufReader>

    /**
     * 获取对象的写入流
     *
     * 如果不是文件对象将导致出现错误
     *
     */
    fun writer(): Result<BufWriter>

    /**
     * 删除对象
     */
    fun delete(): Result<Boolean>

    /**
     * 将此路径转换为 符合 RFC 1738 的 url (会剔除敏感信息)
     */
    fun toURL(): String
}
