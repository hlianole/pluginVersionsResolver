package com.hlianole.jetbrains.internship.pluginVersionsResolver.controller

import com.hlianole.jetbrains.internship.pluginVersionsResolver.dto.PlatformCreateRequest
import com.hlianole.jetbrains.internship.pluginVersionsResolver.dto.PluginCreateRequest
import com.hlianole.jetbrains.internship.pluginVersionsResolver.dto.PluginVersionCreateRequest
import com.hlianole.jetbrains.internship.pluginVersionsResolver.dto.SpecificPluginVersionDTO
import com.hlianole.jetbrains.internship.pluginVersionsResolver.model.Either
import com.hlianole.jetbrains.internship.pluginVersionsResolver.service.IPluginService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController

@RestController
class PluginController(
    private val pluginService: IPluginService
): BaseController(), PluginApi {

    override fun getAllPlugins(): ResponseEntity<List<SpecificPluginVersionDTO>> {
        return withCatchBlock {
            ResponseEntity.ok(pluginService.findAll())
        }
    }

    override fun getPluginById(id: Long): ResponseEntity<List<SpecificPluginVersionDTO>> {
        return withCatchBlock {
            when (val found = pluginService.findById(id)) {
                is Either.Left -> ResponseEntity.notFound().build()
                is Either.Right -> ResponseEntity.ok(found.value)
            }
        }
    }

    override fun createPlugin(plugin: PluginCreateRequest): ResponseEntity<Long> {
        return withCatchBlock {
            when (val created = pluginService.save(plugin)) {
                is Either.Left -> ResponseEntity.badRequest().build()
                is Either.Right -> ResponseEntity.ok(created.value)
            }
        }
    }

    override fun deletePlugin(id: Long): ResponseEntity<Nothing> {
        return deletion {
            pluginService.delete(id)
        }
    }

    override fun addNewVersion(pluginId: Long, version: PluginVersionCreateRequest): ResponseEntity<Long> {
        return withCatchBlock {
            when (val created = pluginService.addVersion(pluginId, version)) {
                is Either.Left -> ResponseEntity.badRequest().build()
                is Either.Right -> ResponseEntity.ok(created.value)
            }
        }
    }

    override fun deleteVersion(id: Long): ResponseEntity<Nothing> {
        return deletion {
            pluginService.deleteVersion(id)
        }
    }

    override fun addNewPlatform(versionId: Long, platform: PlatformCreateRequest): ResponseEntity<Long> {
        return withCatchBlock {
            when (val created = pluginService.addVariant(versionId, platform)) {
                is Either.Left -> ResponseEntity.badRequest().build()
                is Either.Right -> ResponseEntity.ok(created.value)
            }
        }
    }

    override fun deletePlatform(id: Long): ResponseEntity<Nothing> {
        return deletion {
            pluginService.deleteVariant(id)
        }
    }

    override fun getSpecificPlugin(
        query: String,
        os: String?,
        arch: String?
    ): ResponseEntity<List<SpecificPluginVersionDTO>> {
        return withCatchBlock {
            val found = pluginService.getMatches(query, os, arch)
            return ResponseEntity.ok(found)
        }
    }

}