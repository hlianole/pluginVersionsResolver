package com.hlianole.jetbrains.internship.pluginVersionsResolver.service

import com.hlianole.jetbrains.internship.pluginVersionsResolver.dto.PlatformDTO
import com.hlianole.jetbrains.internship.pluginVersionsResolver.dto.SpecificPluginVersionDTO
import com.hlianole.jetbrains.internship.pluginVersionsResolver.dto.SpecificVersionDTO
import com.hlianole.jetbrains.internship.pluginVersionsResolver.model.Plugin
import com.hlianole.jetbrains.internship.pluginVersionsResolver.model.PluginVersion
import java.time.format.DateTimeFormatter

object CompatibilityResolver {
    fun resolve(plugin: Plugin, os: String?, arch: String?): List<SpecificPluginVersionDTO> {
        val res = mutableListOf<SpecificPluginVersionDTO>()
        plugin.versions.forEach { version ->
            version.platformVariants.forEach { variant ->
                val variantOs = variant.platform?.os?.name
                val variantArch = variant.platform?.arch?.name

                if (filterOs(variantOs, os) && filterArch(variantArch, arch)) {
                    res.add(createDto(plugin, version, variantOs, variantArch))
                }
            }
        }
        return res
    }

    private fun createDto(
        plugin: Plugin,
        version: PluginVersion,
        variantOs: String?,
        variantArch: String?
    ) = SpecificPluginVersionDTO(
        id = plugin.id,
        artifactId = plugin.artifactId,
        description = plugin.description,
        vendor = plugin.vendor,
        version = SpecificVersionDTO(
            id = version.id,
            name = version.name,
            // -||-
            releaseDate = version.releaseDate.format(DateTimeFormatter.ISO_DATE_TIME),
        ),
        platform = PlatformDTO(
            os = variantOs,
            arch = variantArch,
        ),
    )

    private fun filterOs(variantOs: String?, os: String?): Boolean {
        if (variantOs == null || os == null) {
            return true
        }
        return variantOs.equals(os, ignoreCase = true)
    }

    private fun filterArch(variantArch: String?, arch: String?): Boolean {
        if (variantArch == null || arch == null) {
            return true
        }
        return variantArch.equals(arch, ignoreCase = true)
    }
}