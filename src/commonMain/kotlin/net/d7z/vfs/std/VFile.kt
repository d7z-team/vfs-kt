package net.d7z.vfs.std

import net.d7z.vfs.io.BufReader
import net.d7z.vfs.io.BufWriter
import org.d7z.utils.CommonOptional

/**
 * 虚拟对象
 */
interface VFile {
    /**
     * 对象名称
     */
    val name: String

    /**
     * 对象路径
     *
     * 所有对象路径均为 `/` 开始，用 `/` 分割，其余部分使用utf8 编码，不支持控制字符
     */
    val path: String

    /**
     * 对象大小（如果为目录则永远等于 0）
     */
    val size: Long

    /**
     * 此路径是否存在对象
     *
     * 如果此路径下无任何文件/子目录则返回 false
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
    fun parent(): CommonOptional<VFile>

    /**
     * 获取子路径对象
     *
     */
    fun child(path: String): Result<VFile>

    /**
     * 获取对象的读取流
     *
     * 如果不是文件对象将返回错误信息
     *
     */
    fun reader(): Result<BufReader>

    /**
     * 获取对象的写入流
     *
     * 注意，某些实现不支持多线程，这将导致多线程写入出现问题，
     * 请考虑使用其他 API
     *
     * @param create Boolean 如果文件不存在是否创建
     * @param append Boolean 是否置为追加模式
     */
    fun writer(create: Boolean = true, append: Boolean = false): Result<BufWriter>

    /**
     * 删除对象,如果是目录将会删除下面的所有文件
     *
     */
    fun delete(): Result<Unit>

    /**
     * 列出当前目录下的所有文件
     *
     * 如果实现不支持列出文件列表或当前路径为文件对象将返回异常，否则将返回对应结果或空列表
     *
     * 如果此目录的子目录无文件将不会列出！
     *
     * @param filter Function1<VFile, Boolean> 文件过滤器
     * @return 最终结果
     */
    fun listFiles(filter: (VFile) -> Boolean = { true }): Result<List<VFile>>

    /**
     * 将此路径转换为 符合 RFC 1738 的 url
     */
    fun toURL(): String
}
