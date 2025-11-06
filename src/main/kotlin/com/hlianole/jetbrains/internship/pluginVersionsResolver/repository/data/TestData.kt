package com.hlianole.jetbrains.internship.pluginVersionsResolver.repository.data

import com.hlianole.jetbrains.internship.pluginVersionsResolver.model.*
import java.time.LocalDateTime

/**
 * List of plugins that can be used to test functionality of the application.
 * */
val plugins = listOf(
    Plugin(
        id = -1,
        artifactId = "test.plugin",
        description = "First test plugin. Lorem ipsum dolor sit amet",
        vendor = "hlianole",
        versions = mutableListOf(
            PluginVersion(
                id = -1,
                pluginId = -1,
                name = "1.0.0",
                releaseDate = LocalDateTime.now(),
                platformVariants = mutableListOf(
                    PluginPlatformVariant(
                        id = -1,
                        versionId = -1,
                        platform = Platform(
                            os = OS.LINUX,
                            arch = null,
                        )
                    ),
                    PluginPlatformVariant(
                        id = -1,
                        versionId = -1,
                        platform = Platform(
                            os = OS.MACOS,
                            arch = Architecture.ARM64,
                        )
                    )
                )
            ),
            PluginVersion(
                id = -1,
                pluginId = -1,
                name = "2.0.0",
                releaseDate = LocalDateTime.now(),
                platformVariants = mutableListOf()
            ),
            PluginVersion(
                id = -1,
                pluginId = -1,
                name = "2.3.1",
                releaseDate = LocalDateTime.now(),
                platformVariants = mutableListOf(
                    PluginPlatformVariant(
                        id = -1,
                        versionId = -1,
                        platform = null
                    ),
                    PluginPlatformVariant(
                        id = -1,
                        versionId = -1,
                        platform = Platform(
                            os = OS.MACOS,
                            arch = Architecture.ARM64,
                        )
                    ),
                    PluginPlatformVariant(
                        id = -1,
                        versionId = -1,
                        platform = Platform(
                            os = OS.WINDOWS,
                            arch = Architecture.X86,
                        )
                    ),
                    PluginPlatformVariant(
                        id = -1,
                        versionId = -1,
                        platform = Platform(
                            os = OS.LINUX,
                            arch = Architecture.X64,
                        )
                    ),
                    PluginPlatformVariant(
                        id = -1,
                        versionId = -1,
                        platform = Platform(
                            os = OS.LINUX,
                            arch = Architecture.ARM64,
                        )
                    ),
                    PluginPlatformVariant(
                        id = -1,
                        versionId = -1,
                        platform = Platform(
                            os = OS.LINUX,
                            arch = null
                        )
                    )
                )
            )
        )
    ),
    Plugin(
        id = -1,
        artifactId = "super.plugin",
        description = "Another super plugin. Lorem ipsum dolor sit amet...",
        vendor = "super s.r.o",
        versions = mutableListOf(
            PluginVersion(
                id = -1,
                pluginId = -1,
                name = "0.1.0-BETA",
                releaseDate = LocalDateTime.now(),
                platformVariants = mutableListOf(
                    PluginPlatformVariant(
                        id = -1,
                        versionId = -1,
                        platform = null
                    ),
                    PluginPlatformVariant(
                        id = -1,
                        versionId = -1,
                        platform = Platform(
                            os = OS.MACOS,
                            arch = Architecture.ARM64,
                        )
                    )
                )
            ),
            PluginVersion(
                id = -1,
                pluginId = -1,
                name = "1.5.2",
                releaseDate = LocalDateTime.now(),
                platformVariants = mutableListOf(
                    PluginPlatformVariant(
                        id = -1,
                        versionId = -1,
                        platform = Platform(
                            os = OS.MACOS,
                            arch = Architecture.X86,
                        )
                    ),
                    PluginPlatformVariant(
                        id = -1,
                        versionId = -1,
                        platform = Platform(
                            os = OS.MACOS,
                            arch = Architecture.ARM64,
                        )
                    ),
                    PluginPlatformVariant(
                        id = -1,
                        versionId = -1,
                        platform = Platform(
                            os = OS.WINDOWS,
                            arch = Architecture.X86,
                        )
                    )
                )
            )
        ),
    ),
    Plugin(
        id = -1,
        artifactId = "very.useful.plugin",
        description = "Useful plugin. Lorem ipsum dolor sit amet!",
        vendor = "hlianole",
        versions = mutableListOf(
            PluginVersion(
                id = -1,
                pluginId = -1,
                name = "0.0.1",
                releaseDate = LocalDateTime.now(),
                platformVariants = mutableListOf(),
            ),
            PluginVersion(
                id = -1,
                pluginId = -1,
                name = "0.1.0",
                releaseDate = LocalDateTime.now(),
                platformVariants = mutableListOf(
                    PluginPlatformVariant(
                        id = -1,
                        versionId = -1,
                        platform = Platform(
                            os = OS.LINUX,
                            arch = null,
                        )
                    )
                )
            ),
            PluginVersion(
                id = -1,
                pluginId = -1,
                name = "1.0.0",
                releaseDate = LocalDateTime.now(),
                platformVariants = mutableListOf(
                    PluginPlatformVariant(
                        id = -1,
                        versionId = -1,
                        platform = Platform(
                            os = OS.MACOS,
                            arch = Architecture.X86,
                        )
                    ),
                    PluginPlatformVariant(
                        id = -1,
                        versionId = -1,
                        platform = Platform(
                            os = OS.MACOS,
                            arch = Architecture.ARM64,
                        )
                    )
                )
            )
        ),
    ),
    Plugin(
        id = -1,
        artifactId = "only.windows.plugin",
        description = "Windows only.",
        vendor = "microsoft",
        versions = mutableListOf(
            PluginVersion(
                id = -1,
                pluginId = -1,
                name = "0.1.0-BETA",
                releaseDate = LocalDateTime.now(),
                platformVariants = mutableListOf(
                    PluginPlatformVariant(
                        id = -1,
                        versionId = -1,
                        platform = Platform(
                            os = OS.WINDOWS,
                            arch = Architecture.X64,
                        )
                    )
                ),
            ),
            PluginVersion(
                id = -1,
                pluginId = -1,
                name = "1.0.0-BETA",
                releaseDate = LocalDateTime.now(),
                platformVariants = mutableListOf(
                    PluginPlatformVariant(
                        id = -1,
                        versionId = -1,
                        platform = Platform(
                            os = OS.WINDOWS,
                            arch = Architecture.X86,
                        )
                    )
                ),
            )
        ),
    )
)