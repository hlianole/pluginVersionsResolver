package com.hlianole.jetbrains.internship.pluginVersionsResolver.repository

import com.hlianole.jetbrains.internship.pluginVersionsResolver.model.Plugin
import com.hlianole.jetbrains.internship.pluginVersionsResolver.model.PluginPlatformVariant
import com.hlianole.jetbrains.internship.pluginVersionsResolver.model.PluginVersion

interface IPluginRepository {

    /**
     * @return list containing all saved plugins
     * */
    fun findAll(): List<Plugin>

    /**
     * @return [Plugin] associated to the specific id. `null` if there is no plugin with this id
     * */
    fun findById(id: Long): Plugin?

    /**
     * @return new saved [Plugin]. `null` if saving failed
     * */
    fun save(plugin: Plugin): Plugin?

    /**
     * @return `true` if deletion succeeded. `false` otherwise.
     * */
    fun delete(id: Long): Boolean

    /**
     * @return new saved [PluginVersion]. `null` if saving failed
     * */
    fun addVersion(version: PluginVersion): PluginVersion?

    /**
     * @return `true` if deletion succeeded. `false` otherwise.
     * */
    fun deleteVersion(id: Long): Boolean

    /**
     * @return new saved [PluginPlatformVariant]. `null` if saving failed
     * */
    fun addVariant(variant: PluginPlatformVariant): PluginPlatformVariant?

    /**
     * @return `true` if deletion succeeded. `false` otherwise.
     * */
    fun deleteVariant(id: Long): Boolean

}