package com.hlianole.jetbrains.internship.pluginVersionsResolver.dto

import com.hlianole.jetbrains.internship.pluginVersionsResolver.model.Plugin
import kotlinx.serialization.Serializable
import java.time.format.DateTimeFormatter

/**
 * Represents a single version of a plugin with a specific os/arch.
 * */
@Serializable
data class SpecificPluginVersionDTO (
    val id: Long,
    val artifactId: String,
    val description: String? = null,
    val vendor: String,
    val version: SpecificVersionDTO,
    val platform: PlatformDTO? = null,
)

@Serializable
data class SpecificVersionDTO (
    val id: Long,
    val name: String,
    val releaseDate: String,
)

@Serializable
data class PlatformDTO (
    val id: Long,
    val os: String? = null,
    val arch: String? = null,
)

val BASIC_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")

fun List<Plugin>.toDto(): List<SpecificPluginVersionDTO> {
    return this.flatMap {
        it.toDto()
    }
}

fun Plugin.toDto(): List<SpecificPluginVersionDTO> {
    return this.versions.flatMap { version ->
        version.platformVariants.map { variant ->
            SpecificPluginVersionDTO(
                id = this.id,
                artifactId = this.artifactId,
                description = this.description,
                vendor = this.vendor,
                version = SpecificVersionDTO(
                    id = version.id,
                    name = version.name,
                    releaseDate = version.releaseDate.format(BASIC_FORMATTER),
                ),
                platform = PlatformDTO(
                    id = variant.id,
                    os = variant.platform?.os?.toString(),
                    arch = variant.platform?.arch?.toString(),
                ),
            )
        }
    }
}