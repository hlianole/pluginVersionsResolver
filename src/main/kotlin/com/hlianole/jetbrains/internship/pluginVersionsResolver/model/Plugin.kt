package com.hlianole.jetbrains.internship.pluginVersionsResolver.model

import java.time.LocalDateTime

data class Plugin(
    val id: Long,
    val artifactId: String,
    val description: String?,
    val vendor: String,
    val versions: MutableList<PluginVersion>,
)

data class PluginVersion(
    val id: Long,
    val pluginId: Long,
    val name: String,
    val releaseDate: LocalDateTime,
    val platformVariants: MutableList<PluginPlatformVariant>
)

data class PluginPlatformVariant(
    val id: Long,
    val versionId: Long,
    val platform: Platform?,
)

fun PluginVersion.newer(other: PluginVersion): Boolean {
    val regex = Regex("""^(\d+)\.(\d+)\.(\d+)(?:[-.]?([A-Za-z0-9]+))?$""")

    fun parse(entry: String): ParsedVersion? {
        val match = regex.matchEntire(entry) ?: return null

        val (major, minor, patch, suffix) = match.destructured
        return ParsedVersion(
            major.toInt(), minor.toInt(), patch.toInt(), suffix.ifEmpty { null }
        )
    }

    val my = parse(this.name)
    val oth = parse(other.name)

    if (my == null || oth == null) {
        return false
    }

    return when {
        my.major != oth.major -> my.major > oth.major
        my.minor != oth.minor -> my.minor > oth.minor
        my.patch != oth.patch -> my.patch > oth.patch
        else -> false
    }
}

private data class ParsedVersion(
    val major: Int,
    val minor: Int,
    val patch: Int,
    val suffix: String?,
)