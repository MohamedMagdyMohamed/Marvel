package com.example.marvel.utils.extentions

private const val HEX_JOIN_CONSTANT_FORMAT = "%02x"

fun ByteArray.toHexString(): String {
    return joinToString("") { HEX_JOIN_CONSTANT_FORMAT.format(it) }
}
