package com.hlianole.jetbrains.internship.pluginVersionsResolver.unit

import com.hlianole.jetbrains.internship.pluginVersionsResolver.dto.*
import com.hlianole.jetbrains.internship.pluginVersionsResolver.model.*
import com.hlianole.jetbrains.internship.pluginVersionsResolver.repository.IPluginRepository
import com.hlianole.jetbrains.internship.pluginVersionsResolver.repository.data.plugins
import com.hlianole.jetbrains.internship.pluginVersionsResolver.service.CompatibilityResolver
import com.hlianole.jetbrains.internship.pluginVersionsResolver.service.IPluginServiceImpl
import io.mockk.every
import io.mockk.mockkObject
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever
import java.time.LocalDateTime
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue
import kotlin.test.fail

class PluginServiceTest {

    @Mock
    private lateinit var repository: IPluginRepository

    @InjectMocks
    private lateinit var service: IPluginServiceImpl

    private val createRequest = PluginCreateRequest(
        artifactId = "test",
        description = "Description",
        vendor = "hlianole",
        versionName = "1.0.0",
        platform = PlatformCreateRequest(
            os = "macos",
            arch = "x86",
        )
    )

    private val invalidCreateRequest = PluginCreateRequest(
        artifactId = "test",
        description = "Description",
        vendor = "hlianole",
        versionName = "1.0.0.0.0",
        platform = PlatformCreateRequest(
            os = "macos",
            arch = "x86",
        )
    )

    private val createdPlugin = Plugin(
        id = 0,
        artifactId = "test",
        description = "Description",
        vendor = "hlianole",
        versions = mutableListOf(
            PluginVersion(
                id = 0,
                pluginId = 0,
                name = "1.0.0",
                releaseDate = LocalDateTime.now(),
                platformVariants = mutableListOf(
                    PluginPlatformVariant(
                        id = 0,
                        versionId = 0,
                        platform = Platform(
                            os = OS.MACOS,
                            arch = Architecture.X86,
                        )
                    )
                )
            )
        )
    )

    private val match = SpecificPluginVersionDTO(
        id = 0,
        artifactId = "test.plugin",
        description = "First test plugin. Lorem ipsum dolor sit amet",
        vendor = "hlianole",
        version = SpecificVersionDTO(
            id = -1,
            name = "2.3.1",
            releaseDate = LocalDateTime.now().format(BASIC_FORMATTER),
        ),
        platform = PlatformDTO(
            id = -1,
            os = "MACOS",
            arch = "ARM64",
        ),
    )

    @Test
    fun shouldReturnAllPlugins() {
        whenever(repository.findAll()) doReturn plugins

        val result = service.findAll()

        assertTrue(result.isNotEmpty())
        assertEquals(plugins.toDto(), result)
    }

    @Test
    fun shouldFindById() {
        whenever(repository.findById(0)) doReturn plugins[0]

        val result = service.findById(0)

        assertNotEquals(Either.Left(PluginError.NotFound), result)
        assertEquals(Either.Right(plugins[0].toDto()), result)
    }

    @Test
    fun shouldNotFindById() {
        whenever(repository.findById(28)) doReturn null

        val result = service.findById(28)

        assertEquals(Either.Left(PluginError.NotFound), result)
    }

    @Test
    fun shouldSavePlugin() {
        whenever(repository.save(createRequest.toDomain())) doReturn createdPlugin

        val result = service.save(createRequest)

        assertNotEquals(Either.Left(PluginError.InvalidRequest), result)
        assertEquals(Either.Right(0L), result)
    }

    @Test
    fun shouldNotSavePlugin() {
        whenever(repository.save(invalidCreateRequest.toDomain())) doReturn null

        val result = service.save(invalidCreateRequest)

        assertEquals(Either.Left(PluginError.InvalidRequest), result)
    }

    @Test
    fun shouldFindMatches() {
        mockkObject(CompatibilityResolver)

        whenever(repository.findAll()) doReturn plugins
        every { CompatibilityResolver.resolve(plugins[0], "macos", "arm64") } returns match
        every { CompatibilityResolver.resolve(any(), any(), any()) } answers {
            fail("Unexpected call to CompatibilityResolver.resolve(${args.joinToString()})")
        }

        val result = service.getMatches("test.plugin", "macos", "arm64")

        assertEquals(1, result.size)
        assertEquals(listOf(match), result)
    }

}