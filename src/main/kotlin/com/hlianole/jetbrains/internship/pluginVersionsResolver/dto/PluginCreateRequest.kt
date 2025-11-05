package com.hlianole.jetbrains.internship.pluginVersionsResolver.dto

import com.hlianole.jetbrains.internship.pluginVersionsResolver.model.Platform
import com.hlianole.jetbrains.internship.pluginVersionsResolver.model.Plugin
import com.hlianole.jetbrains.internship.pluginVersionsResolver.model.PluginPlatformVariant
import com.hlianole.jetbrains.internship.pluginVersionsResolver.model.PluginVersion
import java.time.LocalDateTime

data class PluginCreateRequest(
    val artifactId: String,
    val description: String?,
    val vendor: String,
    val versionName: String,
    val platform: Platform?,
)

fun PluginCreateRequest.toDomain(): Plugin {
    return Plugin(
        id = -1,
        artifactId = artifactId,
        description = description,
        vendor = vendor,
        versions = listOf(
            PluginVersion(
                id = -1,
                pluginId = -1,
                name = versionName,
                releaseDate = LocalDateTime.now(),
                platformVariants = if (platform != null) {
                    listOf(
                        PluginPlatformVariant(
                            id = -1,
                            versionId = -1,
                            platform = platform,
                        )
                    )
                } else {
                    emptyList()
                },
            )
        )
    )
}
