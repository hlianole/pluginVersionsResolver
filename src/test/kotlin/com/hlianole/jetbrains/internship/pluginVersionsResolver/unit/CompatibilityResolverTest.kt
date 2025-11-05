package com.hlianole.jetbrains.internship.pluginVersionsResolver.unit

import com.hlianole.jetbrains.internship.pluginVersionsResolver.repository.data.plugins
import com.hlianole.jetbrains.internship.pluginVersionsResolver.service.CompatibilityResolver
import org.junit.jupiter.api.assertNull
import kotlin.test.Test
import kotlin.test.assertEquals

class CompatibilityResolverTest {

    @Test
    fun shouldReturnNewestVersion() {
        val result = CompatibilityResolver.resolve(plugins[3], null, null)

        assertEquals("1.0.0", result?.version?.name)
    }

    @Test
    fun shouldReturnSpecificVersion() {
        val result = CompatibilityResolver.resolve(plugins[3], "macos", "arm64")

        assertEquals("1.0.0", result?.version?.name)
        assertEquals("MACOS", result?.platform?.os)
        assertEquals("ARM64", result?.platform?.arch)
    }

    @Test
    fun shouldReturnOlderVersion() {
        val result = CompatibilityResolver.resolve(plugins[3], "linux", "arm64")

        assertEquals("0.1.0", result?.version?.name)
        assertEquals("LINUX", result?.platform?.os)
        assertEquals(null, result?.platform?.arch)
    }

    @Test
    fun shouldReturnTheOldestVersion() {
        val result = CompatibilityResolver.resolve(plugins[3], "WINDOWS", null)

        assertEquals("0.0.1", result?.version?.name)
    }

    @Test
    fun shouldReturnNull() {
        val result = CompatibilityResolver.resolve(plugins[4], "MACOS", null)

        assertNull(result)
    }

}