package com.hlianole.jetbrains.internship.pluginVersionsResolver.service

import com.hlianole.jetbrains.internship.pluginVersionsResolver.dto.BASIC_FORMATTER
import com.hlianole.jetbrains.internship.pluginVersionsResolver.dto.PlatformDTO
import com.hlianole.jetbrains.internship.pluginVersionsResolver.dto.SpecificPluginVersionDTO
import com.hlianole.jetbrains.internship.pluginVersionsResolver.dto.SpecificVersionDTO
import com.hlianole.jetbrains.internship.pluginVersionsResolver.model.Plugin
import com.hlianole.jetbrains.internship.pluginVersionsResolver.model.PluginVersion

object CompatibilityResolver {
    fun resolve(plugin: Plugin, os: String?, arch: String?): List<SpecificPluginVersionDTO> {
        val res = mutableListOf<SpecificPluginVersionDTO>()
        plugin.versions.forEach { version ->
            if (version.platformVariants.isEmpty()) {
                res.add(createDto(plugin, version, null))
            }
            version.platformVariants.forEach { variant ->
                val variantOs = variant.platform?.os?.name
                val variantArch = variant.platform?.arch?.name

                if (filterOs(variantOs, os) && filterArch(variantArch, arch)) {
                    res.add(createDto(plugin, version, PlatformDTO(variant.id, variantOs, variantArch)))
                }
            }
        }
        return res
    }

    private fun createDto(
        plugin: Plugin,
        version: PluginVersion,
        platform: PlatformDTO?
    ) = SpecificPluginVersionDTO(
        id = plugin.id,
        artifactId = plugin.artifactId,
        description = plugin.description,
        vendor = plugin.vendor,
        version = SpecificVersionDTO(
            id = version.id,
            name = version.name,
            releaseDate = version.releaseDate.format(BASIC_FORMATTER),
        ),
        platform = platform,
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