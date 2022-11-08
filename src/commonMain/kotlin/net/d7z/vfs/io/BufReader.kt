package net.d7z.vfs.io

interface BufReader {
    /**
     * 读取数据，存入缓冲区下
     */
    fun read(buffer: ByteArray): Int = read(buffer, 0, buffer.size)

    /**
     * 读取数据，存入缓冲区下
     *
     * @param buffer ByteArray 缓冲区
     * @param offset Int 缓冲区偏移量
     * @param size Int 预期读取数量
     * @return Int 实际读取数量
     */
    fun read(buffer: ByteArray, offset: Int, size: Int): Int

    /**
     * 销毁上下文
     */
    fun close()
}
