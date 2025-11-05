package com.hlianole.jetbrains.internship.pluginVersionsResolver.dto

import com.hlianole.jetbrains.internship.pluginVersionsResolver.model.PluginVersion
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class PluginVersionCreateRequest(
    val name: String,
    val variants: List<PlatformCreateRequest>
)

fun PluginVersionCreateRequest.toDomain(): PluginVersion {
    return PluginVersion(
        id = -1,
        pluginId = -1,
        name = name,
        releaseDate = LocalDateTime.now(),
        platformVariants = variants.map {
            it.toDomain()
        }.toMutableList(),
    )
}