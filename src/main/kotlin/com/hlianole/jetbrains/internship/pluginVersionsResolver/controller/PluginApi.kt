package com.hlianole.jetbrains.internship.pluginVersionsResolver.controller

import com.hlianole.jetbrains.internship.pluginVersionsResolver.dto.PlatformCreateRequest
import com.hlianole.jetbrains.internship.pluginVersionsResolver.dto.PluginCreateRequest
import com.hlianole.jetbrains.internship.pluginVersionsResolver.dto.PluginVersionCreateRequest
import com.hlianole.jetbrains.internship.pluginVersionsResolver.dto.SpecificPluginVersionDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam

@Validated
@RequestMapping("/api/plugins")
interface PluginApi {

    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/all"],
        produces = ["application/json"]
    )
    fun getAllPlugins(): ResponseEntity<List<SpecificPluginVersionDTO>> =
        ResponseEntity(HttpStatus.NOT_IMPLEMENTED)


    @RequestMapping(
        method = [RequestMethod.GET],
        value = [""],
        produces = ["application/json"]
    )
    fun getPluginById(@RequestHeader(value = "id", required = true) id: Long): ResponseEntity<List<SpecificPluginVersionDTO>> =
        ResponseEntity(HttpStatus.NOT_IMPLEMENTED)


    @RequestMapping(
        method = [RequestMethod.POST],
        value = [""],
        consumes = ["application/json"]
    )
    fun createPlugin(@RequestBody plugin: PluginCreateRequest): ResponseEntity<Long> =
        ResponseEntity(HttpStatus.NOT_IMPLEMENTED)


    @RequestMapping(
        method = [RequestMethod.DELETE],
        value = [""]
    )
    fun deletePlugin(@RequestHeader(value = "id", required = true) id: Long): ResponseEntity<Nothing> =
        ResponseEntity(HttpStatus.NOT_IMPLEMENTED)


    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/version"],
        consumes = ["application/json"]
    )
    fun addNewVersion(
        @RequestHeader(value = "pluginId", required = true) pluginId: Long,
        @RequestBody version: PluginVersionCreateRequest
    ): ResponseEntity<Long> =
        ResponseEntity(HttpStatus.NOT_IMPLEMENTED)


    @RequestMapping(
        method = [RequestMethod.DELETE],
        value = ["/version"],
    )
    fun deleteVersion(
        @RequestHeader(value = "id", required = true) id: Long,
    ): ResponseEntity<Nothing> =
        ResponseEntity(HttpStatus.NOT_IMPLEMENTED)


    @RequestMapping(
        method = [RequestMethod.POST],
        value = ["/platform"],
        consumes = ["application/json"]
    )
    fun addNewPlatform(
        @RequestHeader(value = "versionId", required = true) versionId: Long,
        @RequestBody platform: PlatformCreateRequest
    ): ResponseEntity<Long> =
        ResponseEntity(HttpStatus.NOT_IMPLEMENTED)


    @RequestMapping(
        method = [RequestMethod.DELETE],
        value = ["/platform"],
        consumes = ["application/json"]
    )
    fun deletePlatform(
        @RequestHeader(value = "id", required = true) id: Long,
    ): ResponseEntity<Nothing> =
        ResponseEntity(HttpStatus.NOT_IMPLEMENTED)


    @RequestMapping(
        method = [RequestMethod.GET],
        value = ["/search"],
        produces = ["application/json"]
    )
    fun getSpecificPlugin(
        @RequestParam query: String,
        @RequestParam os: String?,
        @RequestParam arch: String?,
    ): ResponseEntity<List<SpecificPluginVersionDTO>> =
        ResponseEntity(HttpStatus.NOT_IMPLEMENTED)
}