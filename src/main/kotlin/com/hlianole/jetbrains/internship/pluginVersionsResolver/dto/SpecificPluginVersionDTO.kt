package com.hlianole.jetbrains.internship.pluginVersionsResolver.dto

import com.hlianole.jetbrains.internship.pluginVersionsResolver.model.Plugin
import java.time.format.DateTimeFormatter

data class SpecificPluginVersionDTO (
    val id: Long,
    val artifactId: String,
    val description: String? = null,
    val vendor: String,
    val version: SpecificVersionDTO,
    val platform: PlatformDTO? = null,
)

val BASIC_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")

data class SpecificVersionDTO (
    val id: Long,
    val name: String,
    val releaseDate: String,
)

data class PlatformDTO (
    val os: String? = null,
    val arch: String? = null,
)

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
                    os = variant.platform?.os?.toString(),
                    arch = variant.platform?.arch?.toString(),
                ),
            )
        }
    }
}