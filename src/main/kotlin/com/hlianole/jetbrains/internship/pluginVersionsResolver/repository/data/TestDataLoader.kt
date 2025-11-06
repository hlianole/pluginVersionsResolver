package com.hlianole.jetbrains.internship.pluginVersionsResolver.repository.data

import com.hlianole.jetbrains.internship.pluginVersionsResolver.repository.IPluginRepository
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component

/**
 * Loads [plugins] to the memory.
 * */
@Component
class TestDataLoader(
    private val pluginRepository: IPluginRepository
) {

    @PostConstruct
    fun load() {
        plugins.forEach {
            pluginRepository.save(it)
        }
    }

}