package com.hlianole.jetbrains.internship.pluginVersionsResolver.service

import com.hlianole.jetbrains.internship.pluginVersionsResolver.dto.BASIC_FORMATTER
import com.hlianole.jetbrains.internship.pluginVersionsResolver.dto.PlatformDTO
import com.hlianole.jetbrains.internship.pluginVersionsResolver.dto.SpecificPluginVersionDTO
import com.hlianole.jetbrains.internship.pluginVersionsResolver.dto.SpecificVersionDTO
import com.hlianole.jetbrains.internship.pluginVersionsResolver.model.Plugin
import com.hlianole.jetbrains.internship.pluginVersionsResolver.model.PluginVersion

/**
 * Searches for the best match between plugin's specific version.
 *
 * @return [SpecificPluginVersionDTO]. Priority is the latest version. But if request contains specific os/arch, an older version can be returned, if
 * later versions do not contain the desired combination of os and arch. `null` will be returned if the versions
 * does not contain the desired combination.
 * */
object CompatibilityResolver {
    fun resolve(plugin: Plugin, os: String?, arch: String?): SpecificPluginVersionDTO? {
        plugin.versions.reversed().forEach { version ->
            if (version.platformVariants.isEmpty()) {
                return createDto(plugin, version, null)
            }

            val filtered = version.platformVariants
                .filter { variant ->
                filterOs(variant.platform?.os?.name, os) &&
                filterArch(variant.platform?.arch?.name, arch)
            }.sortedWith(compareByDescending { variant ->
                    val osMatch = os != null
                            && variant.platform?.os?.name?.equals(os, ignoreCase = true) == true
                    val archMatch = arch != null
                            && variant.platform?.arch?.name?.equals(arch, ignoreCase = true) == true

                    when {
                        osMatch && archMatch -> 2
                        osMatch -> 1
                        archMatch -> 1
                        else -> 0
                    }
                })

            if (filtered.isNotEmpty()) {
                return createDto(plugin, version, PlatformDTO(
                    filtered.first().id,
                    filtered.first().platform?.os?.name,
                    filtered.first().platform?.arch?.name,
                ))
            }
        }
        return null
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