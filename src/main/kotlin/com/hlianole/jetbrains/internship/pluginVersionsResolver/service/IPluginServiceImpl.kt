package com.hlianole.jetbrains.internship.pluginVersionsResolver.service

import com.hlianole.jetbrains.internship.pluginVersionsResolver.dto.*
import com.hlianole.jetbrains.internship.pluginVersionsResolver.model.*
import com.hlianole.jetbrains.internship.pluginVersionsResolver.repository.IPluginRepository
import org.springframework.stereotype.Service

@Service
class IPluginServiceImpl(
    private val pluginRepository: IPluginRepository,
): IPluginService {
    override fun findAll(): List<SpecificPluginVersionDTO> =
        pluginRepository.findAll().toDto()

    override fun findById(id: Long): Either<PluginError, List<SpecificPluginVersionDTO>> {
        val plugin = pluginRepository.findById(id)
        return if (plugin != null) {
            Either.Right(value = plugin.toDto())
        } else {
            Either.Left(value = PluginError.NotFound)
        }
    }

    override fun save(pluginCreateRequest: PluginCreateRequest): Either<PluginError, Long> {
        val plugin = pluginRepository.save(pluginCreateRequest.toDomain())
            ?: return Either.Left(PluginError.InvalidRequest)
        return Either.Right(plugin.id)
    }

    override fun delete(id: Long) = pluginRepository.delete(id)

    override fun addVersion(pluginId: Long, versionCreateRequest: PluginVersionCreateRequest): Either<PluginError, Long> {
        val version = versionCreateRequest.toDomain().copy(pluginId = pluginId)
        val created = pluginRepository.addVersion(version)
        return if (created == null) {
            Either.Left(PluginError.InvalidRequest)
        } else {
            Either.Right(created.id)
        }
    }

    override fun deleteVersion(id: Long): Boolean = pluginRepository.deleteVersion(id)

    override fun addVariant(versionId: Long, platformCreateRequest: PlatformCreateRequest): Either<PluginError, Long> {
        val variant = platformCreateRequest.toDomain().copy(versionId = versionId)
        val created = pluginRepository.addVariant(variant)
        if (created == null) {
            return Either.Left(PluginError.InvalidRequest)
        } else {
            return Either.Right(created.id)
        }
    }

    override fun deleteVariant(id: Long): Boolean = pluginRepository.deleteVariant(id)

    override fun getMatches(
        query: String, os: String?, arch: String?
    ): List<SpecificPluginVersionDTO> {
        val plugins = pluginRepository.findAll()
        val queried = plugins.filter { plugin ->
            plugin.artifactId.contains(query, ignoreCase = true) ||
            plugin.vendor.contains(query, ignoreCase = true) ||
            plugin.description?.contains(query, ignoreCase = true) ?: false
        }
        val compatible = mutableListOf<SpecificPluginVersionDTO>()
        queried.forEach {
            val allPluginCompatibleVersions = CompatibilityResolver.resolve(it, os, arch)
            compatible.addAll(allPluginCompatibleVersions)
        }

        return compatible
    }
}