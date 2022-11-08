package net.d7z.vfs.io

class ByteArrayBufWriter : BufWriter {
    private val arr = mutableListOf<ByteArray>()
    override fun write(buffer: ByteArray, offset: Int, size: Int) {
        BufWriter.checkBuffer(buffer, offset, size)
        arr.add(buffer.copyOfRange(offset, offset + size))
    }

    fun toByteArray(): ByteArray {
        val dist = ByteArray(arr.sumOf { it.size })
        var point = 0
        arr.forEach {
            it.copyInto(dist, point, 0, it.size)
            point += it.size
        }
        return dist
    }

    override fun close() {
    }
}
