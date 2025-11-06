package com.hlianole.jetbrains.internship.pluginVersionsResolver.service

import com.hlianole.jetbrains.internship.pluginVersionsResolver.dto.PlatformCreateRequest
import com.hlianole.jetbrains.internship.pluginVersionsResolver.dto.PluginCreateRequest
import com.hlianole.jetbrains.internship.pluginVersionsResolver.dto.PluginVersionCreateRequest
import com.hlianole.jetbrains.internship.pluginVersionsResolver.dto.SpecificPluginVersionDTO
import com.hlianole.jetbrains.internship.pluginVersionsResolver.model.Either
import com.hlianole.jetbrains.internship.pluginVersionsResolver.model.PluginError

interface IPluginService {

    fun findAll(): List<SpecificPluginVersionDTO>

    fun findById(id: Long): Either<PluginError, List<SpecificPluginVersionDTO>>

    fun save(pluginCreateRequest: PluginCreateRequest): Either<PluginError, Long>

    fun delete(id: Long): Boolean

    fun addVersion(pluginId: Long, versionCreateRequest: PluginVersionCreateRequest): Either<PluginError, Long>

    fun deleteVersion(id: Long): Boolean

    fun addVariant(versionId: Long, platformCreateRequest: PlatformCreateRequest): Either<PluginError, Long>

    fun deleteVariant(id: Long): Boolean

    /**
     * @return list of the found plugins. Multiple plugins can be returned because it is possible
     * to find different plugins using query.
     * */
    fun getMatches(
        query: String, os: String? = null, arch: String? = null
    ): List<SpecificPluginVersionDTO>
}