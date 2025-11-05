package com.hlianole.jetbrains.internship.pluginVersionsResolver.repository

import com.hlianole.jetbrains.internship.pluginVersionsResolver.model.Plugin
import com.hlianole.jetbrains.internship.pluginVersionsResolver.model.PluginPlatformVariant
import com.hlianole.jetbrains.internship.pluginVersionsResolver.model.PluginVersion

interface IPluginRepository {

    fun findAll(): List<Plugin>

    fun findById(id: Long): Plugin?

    fun save(plugin: Plugin): Plugin?

    fun delete(id: Long): Boolean

    fun addVersion(version: PluginVersion): PluginVersion?

    fun deleteVersion(id: Long): Boolean

    fun addVariant(variant: PluginPlatformVariant): PluginPlatformVariant?

    fun deleteVariant(id: Long): Boolean

}