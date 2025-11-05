package com.hlianole.jetbrains.internship.pluginVersionsResolver.dto

import com.hlianole.jetbrains.internship.pluginVersionsResolver.model.Platform
import com.hlianole.jetbrains.internship.pluginVersionsResolver.model.PluginPlatformVariant
import com.hlianole.jetbrains.internship.pluginVersionsResolver.model.PluginVersion
import java.time.LocalDateTime

data class PluginVersionCreateRequest(
    val name: String,
    val variants: List<Platform>
)

fun PluginVersionCreateRequest.toDomain(): PluginVersion {
    return PluginVersion(
        id = -1,
        pluginId = -1,
        name = name,
        releaseDate = LocalDateTime.now(),
        platformVariants = variants.map {
            PluginPlatformVariant(
                id = -1,
                versionId = -1,
                platform = it
            )
        },
    )
}