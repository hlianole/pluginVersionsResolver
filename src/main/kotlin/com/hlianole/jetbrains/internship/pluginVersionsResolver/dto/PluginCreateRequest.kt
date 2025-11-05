package com.hlianole.jetbrains.internship.pluginVersionsResolver.dto

import com.hlianole.jetbrains.internship.pluginVersionsResolver.model.Plugin
import com.hlianole.jetbrains.internship.pluginVersionsResolver.model.PluginVersion
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class PluginCreateRequest(
    val artifactId: String,
    val description: String?,
    val vendor: String,
    val versionName: String,
    val platform: PlatformCreateRequest?,
)

fun PluginCreateRequest.toDomain(): Plugin {
    return Plugin(
        id = -1,
        artifactId = artifactId,
        description = description,
        vendor = vendor,
        versions = mutableListOf(
            PluginVersion(
                id = -1,
                pluginId = -1,
                name = versionName,
                releaseDate = LocalDateTime.now(),
                platformVariants = if (platform != null) {
                    mutableListOf(
                        platform.toDomain()
                    )
                } else {
                    mutableListOf()
                },
            )
        )
    )
}
