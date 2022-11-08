package net.d7z.vfs.io // ktlint-disable filename

fun BufReader.readAllBytes(): ByteArray {
    val buf = ByteArray(8192)
    return ByteArrayBufWriter().apply {
        var count: Int
        do {
            count = read(buf)
            write(buf, 0, count)
        } while (count != -1)
    }.toByteArray()
}
