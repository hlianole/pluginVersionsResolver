package com.hlianole.jetbrains.internship.pluginVersionsResolver.dto

import com.hlianole.jetbrains.internship.pluginVersionsResolver.model.Architecture
import com.hlianole.jetbrains.internship.pluginVersionsResolver.model.OS
import com.hlianole.jetbrains.internship.pluginVersionsResolver.model.Platform
import com.hlianole.jetbrains.internship.pluginVersionsResolver.model.PluginPlatformVariant
import kotlinx.serialization.Serializable

@Serializable
data class PlatformCreateRequest (
    val os: String?,
    val arch: String?,
)

fun PlatformCreateRequest.toDomain(): PluginPlatformVariant {
    return PluginPlatformVariant(
        id = -1,
        versionId = -1,
        platform = Platform(
            os = if (os == null) null else OS.valueOf(os.uppercase()),
            arch = if (arch == null) null else Architecture.valueOf(arch.uppercase()),
        )
    )
}