// DO NOT MODIFY
// Automatically generated by Arkana (https://github.com/rogerluan/arkana)
package com.arkanakeys

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

internal class ArkanaKeysTest {
    private val salt = listOf(0xcb, 0xe8, 0x21, 0xc1, 0x51, 0xd7, 0xde, 0xc5, 0xe4, 0xf0, 0x9e, 0x52, 0x59, 0x31, 0x91, 0xd7, 0x4f, 0x9a, 0x8e, 0xd0, 0x38, 0x4e, 0xb8, 0x4d, 0x5f, 0xaa, 0x25, 0x8a, 0xc3, 0x81, 0x6e, 0x66, 0xe3, 0xd5, 0xc6, 0x39, 0x4c, 0x70, 0, 0x21, 0x92, 0x7d, 0xfd, 0xb1, 0x4f, 0xa4, 0xe8, 0xa6, 0xf4, 0x54, 0xf0, 0x29, 0x58, 0x49, 0, 0x64, 0xcf, 0xe7, 0xa0, 0xac, 0xc5, 0x6, 0x7d, 0xab)
    private val globalSecrets = ArkanaKeys.Global

    @Test
    fun test_decodeRandomHexKey_shouldDecode() {
        val encoded = listOf(0xfc, 0xd1, 0x42, 0xf1, 0x30, 0xb6, 0xbc, 0xa0, 0xd7, 0xc3, 0xfa, 0x34, 0x6e, 0x9, 0xf3, 0xe4, 0x7b, 0xf8, 0xba, 0xb2, 0xe, 0x2d, 0x88, 0x2c, 0x3c, 0x93, 0x44, 0xe9, 0xfb, 0xb6, 0x8, 0x5f, 0xd0, 0xe4, 0xf0, 0x1, 0x7e, 0x43, 0x36, 0x17, 0xf1, 0x1f, 0xc9, 0x83, 0x78, 0x93, 0x8b, 0x96, 0xcd, 0x66, 0xc8, 0x1c, 0x6a, 0x7e, 0x31, 0x2, 0xae, 0xd6, 0x90, 0xcd, 0xf3, 0x67, 0x1c, 0x9d, 0xae, 0x8d, 0x42, 0xf5, 0x68, 0xb2, 0xbd, 0xf4, 0x85, 0xc6, 0xfc, 0x67, 0x6a, 0x2, 0xf4, 0xb5, 0x7a, 0xa9, 0xe8, 0xe2, 0x9, 0x2c, 0x8a, 0x2c, 0x3e, 0x9d, 0x43, 0xec, 0xfa, 0xb4, 0xc, 0x51, 0x82, 0xe1, 0xf6, 0x5c, 0x2e, 0x12, 0x36, 0x18, 0xf6, 0x18, 0xce, 0x82, 0x77, 0xc1, 0x8d, 0xc3, 0xc0, 0x36, 0xc0, 0x1a, 0x68, 0x7d, 0x35, 0x53, 0xac, 0xd4, 0xc3, 0xcf, 0xf5, 0x65, 0x48, 0x9b)
        assertEquals(ArkanaKeys.decode(encoded = encoded, cipher = salt), "79c0aabe33df78b34b4b6c0ac9ac87f931682366cb4277c09285271fa10a6aa6eec49ec1a6b533eb53f21b2aa7ff95b7a40ebb69de338eee4b030457c3cc0c50")
    }

    @Test
    fun test_decodeRandomBase64Key_shouldDecode() {
        val encoded = listOf(0x84, 0xde, 0x6b, 0xf9, 0x3d, 0xa3, 0xab, 0x87, 0x81, 0x99, 0xdc, 0xb, 0x2c, 0x7c, 0xda, 0x82, 0x2b, 0xc2, 0xf9, 0xb2, 0x59, 0x27, 0xcb, 0x24, 0x6c, 0xc3, 0xe, 0xe6, 0xa4, 0xe3, 0x2d, 0x30, 0xd6, 0xe7, 0x84, 0x43, 0x3c, 0x2, 0x45, 0x13, 0xda, 0x19, 0xaa, 0xc6, 0x7e, 0xd2, 0xa3, 0xe5, 0x9b, 0x60, 0x96, 0x5a, 0x2b, 0x2e, 0x65, 0x6, 0xbf, 0xa3, 0xc6, 0xfd, 0x9f, 0x56, 0x3a, 0xee, 0xa0, 0xbd, 0x64, 0xee, 0x7, 0xb8, 0x86, 0x8c, 0xa8, 0xb9, 0xd9, 0x18, 0x2c, 0x54, 0xd9, 0x8f, 0x3, 0xed, 0xb9, 0xe7, 0x7b, 0x29, 0x85, 0x70)
        assertEquals(ArkanaKeys.decode(encoded = encoded, cipher = salt), "O6J8ltuBeiBYuMKUdXwbaisi3i+lgbCV52BzprE2HdWw1vKCo4fssgebpDfQZPGEkUE/VoXILIGJueHXLw77Cg==")
    }

    @Test
    fun test_decodeUUIDKey_shouldDecode() {
        val encoded = listOf(0xa9, 0xd9, 0x14, 0xa3, 0x37, 0xe3, 0xe6, 0xf7, 0xc9, 0xc5, 0xac, 0x66, 0x3c, 0x1c, 0xa5, 0xe5, 0x7c, 0xfb, 0xa3, 0xe9, 0x1, 0x2b, 0x8a, 0x60, 0x68, 0x9a, 0x47, 0xb2, 0xf5, 0xe7, 0x5a, 0, 0xd3, 0xe7, 0xa4, 0xb)
        assertEquals(ArkanaKeys.decode(encoded = encoded, cipher = salt), "b15bf482-524e-423a-99e2-70b86f4f02b2")
    }

    @Test
    fun test_decodeTrueBoolValue_shouldDecode() {
        val encoded = listOf(0xbf, 0x9a, 0x54, 0xa4)
        assertTrue(ArkanaKeys.decodeBoolean(encoded = encoded, cipher = salt))
    }

    @Test
    fun test_decodeFalseBoolValue_shouldDecode() {
        val encoded = listOf(0xad, 0x89, 0x4d, 0xb2, 0x34)
        assertFalse(ArkanaKeys.decodeBoolean(encoded = encoded, cipher = salt))
    }

    @Test
    fun test_decodeIntValue_shouldDecode() {
        val encoded = listOf(0xff, 0xda)
        assertEquals(ArkanaKeys.decodeInt(encoded = encoded, cipher = salt), 42)
    }

    @Test
    fun test_decodeIntValueWithLeadingZeroes_shouldDecodeAsString() {
        val encoded = listOf(0xfb, 0xd8, 0x11, 0xf0)
        assertEquals(ArkanaKeys.decode(encoded = encoded, cipher = salt), "0001")
    }

    @Test
    fun test_decodeMassiveIntValue_shouldDecodeAsString() {
        val encoded = listOf(0xf2, 0xda, 0x13, 0xf2, 0x62, 0xe0, 0xec, 0xf5, 0xd7, 0xc6, 0xa6, 0x67, 0x6d, 0x6, 0xa6, 0xe2, 0x77, 0xaa, 0xb9, 0xe9, 0xa, 0x7c, 0x8b, 0x7e, 0x68, 0x98, 0x15, 0xb9, 0xf5, 0xb9, 0x5b, 0x52, 0xd4, 0xe2, 0xf3, 0x1, 0x7c, 0x47)
        assertEquals(ArkanaKeys.decode(encoded = encoded, cipher = salt), "92233720368547758079223372036854775807")
    }

    @Test
    fun test_decodeNegativeIntValue_shouldDecodeAsString() {
        val encoded = listOf(0xe6, 0xdc, 0x13)
        assertEquals(ArkanaKeys.decode(encoded = encoded, cipher = salt), "-42")
    }

    @Test
    fun test_decodeFloatingPointValue_shouldDecodeAsString() {
        val encoded = listOf(0xf8, 0xc6, 0x10, 0xf5)
        assertEquals(ArkanaKeys.decode(encoded = encoded, cipher = salt), "3.14")
    }

    @Test
    fun test_encodeAndDecodeValueWithDollarSign_shouldDecode() {
        val encoded = listOf(0xb9, 0x8d, 0x40, 0xad, 0xe, 0xf3, 0xb2, 0xac, 0x89, 0xaf, 0xed, 0x3a, 0x38, 0x55, 0xe8)
        assertEquals(ArkanaKeys.decode(encoded = encoded, cipher = salt), "real_\$lim_shady")
    }
}
