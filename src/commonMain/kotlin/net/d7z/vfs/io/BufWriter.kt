package net.d7z.vfs.io

interface BufWriter {
    fun write(buffer: ByteArray) = write(buffer, 0, buffer.size)

    fun write(buffer: ByteArray, offset: Int, size: Int)

    fun close()

    companion object {
        fun checkBuffer(buffer: ByteArray, offset: Int, size: Int) {
            if (buffer.size < offset + size) {
                throw IndexOutOfBoundsException("buffer size(${buffer.size}) < offset($offset) + size($size)")
            }
        }
    }
}
