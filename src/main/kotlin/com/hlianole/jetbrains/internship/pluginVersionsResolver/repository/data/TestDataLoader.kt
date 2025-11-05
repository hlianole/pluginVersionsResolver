package com.hlianole.jetbrains.internship.pluginVersionsResolver.repository.data

import com.hlianole.jetbrains.internship.pluginVersionsResolver.repository.IPluginRepository
import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Component

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