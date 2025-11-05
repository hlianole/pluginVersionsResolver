package com.hlianole.jetbrains.internship.pluginVersionsResolver.repository

import com.hlianole.jetbrains.internship.pluginVersionsResolver.model.Plugin
import com.hlianole.jetbrains.internship.pluginVersionsResolver.model.PluginPlatformVariant
import com.hlianole.jetbrains.internship.pluginVersionsResolver.model.PluginVersion
import com.hlianole.jetbrains.internship.pluginVersionsResolver.model.newer
import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicLong

@Repository
class InMemoryPluginRepository: IPluginRepository {

    private val plugins: ConcurrentHashMap<Long, Plugin> = ConcurrentHashMap()
    private val pluginNames: ConcurrentHashMap<String, Nothing> = ConcurrentHashMap()
    private val versions: ConcurrentHashMap<Long, PluginVersion> = ConcurrentHashMap()
    private val variants: ConcurrentHashMap<Long, PluginPlatformVariant> = ConcurrentHashMap()

    override fun findAll(): List<Plugin> = plugins.values.toList()

    override fun findById(id: Long): Plugin? = plugins[id]

    override fun save(plugin: Plugin): Plugin? {
        if (pluginNames.containsKey(plugin.artifactId)) {
            return null
        }

        val newPlugin = plugin.copy(id = nextPluginId(), artifactId = plugin.artifactId.lowercase(), versions = mutableListOf())
        plugins[newPlugin.id] = newPlugin
        pluginNames[newPlugin.artifactId]
        plugin.versions.forEach { version ->
            addVersion(version.copy(pluginId = newPlugin.id))
        }
        return plugins[newPlugin.id]
    }

    override fun delete(id: Long): Boolean {
        val plugin = plugins[id] ?: return false

        plugin.versions.forEach {
            deleteVersion(it.id)
        }

        pluginNames.remove(plugin.artifactId)
        plugins.remove(id)
        return true
    }

    override fun addVersion(version: PluginVersion): PluginVersion? {
        val pluginId = version.pluginId
        if (pluginId < 0 || version.id != -1L) {
            return null
        }

        val plugin = plugins[pluginId] ?: return null
        if (plugin.versions.isNotEmpty() && !version.newer(plugin.versions.last())) {
            return null
        }

        val newVersion = version.copy(id = nextVersionId(), platformVariants = mutableListOf())
        versions[newVersion.id] = newVersion
        plugin.versions.add(newVersion)
        version.platformVariants.forEach { variant ->
            addVariant(variant.copy(versionId = newVersion.id))
        }
        return versions[newVersion.id]
    }

    override fun deleteVersion(id: Long): Boolean {
        val version = versions[id] ?: return false
        val plugin = plugins[version.pluginId] ?: return false

        version.platformVariants.forEach {
            variants.remove(it.versionId)
        }

        versions.remove(version.id)
        plugin.versions.remove(version)
        if (plugin.versions.isEmpty()) {
            plugins.remove(plugin.id)
        }
        return true
    }

    override fun addVariant(variant: PluginPlatformVariant): PluginPlatformVariant? {
        val versionId = variant.versionId
        if (versionId < 0 || variant.id != -1L) {
            return null
        }

        val version = versions[versionId] ?: return null
        val pluginId = version.pluginId
        if (pluginId < 0) {
            return null
        }
        plugins[pluginId] ?: return null

        val newVariant = variant.copy(id = nextVariantId())
        version.platformVariants.add(newVariant)
        return newVariant
    }

    override fun deleteVariant(id: Long): Boolean {
        val variant = variants[id] ?: return false
        val version = versions[variant.versionId] ?: return false
        plugins[version.pluginId] ?: return false

        variants.remove(variant.id)
        version.platformVariants.remove(variant)
        return true
    }


    private companion object {
        private var pluginId = AtomicLong(0)
        private var versionId = AtomicLong(0)
        private var variantId = AtomicLong(0)

        fun nextPluginId(): Long {
            return pluginId.getAndIncrement()
        }

        fun nextVersionId(): Long {
            return versionId.getAndIncrement()
        }

        fun nextVariantId(): Long {
            return variantId.getAndIncrement()
        }
    }
}