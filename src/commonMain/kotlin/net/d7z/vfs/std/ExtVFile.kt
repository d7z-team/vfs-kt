package net.d7z.vfs.std

import net.d7z.vfs.io.readAllBytes

/**
 * 读取所有字节
 */
fun VFile.readAllBytes(): ByteArray {
    return reader().getOrThrow().readAllBytes()
}

/**
 * 转换为 UTF-8 字符串
 *
 * @receiver VFile
 * @return String
 */
fun VFile.text(): String {
    return readAllBytes().decodeToString()
}
