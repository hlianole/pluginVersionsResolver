package com.hlianole.jetbrains.internship.pluginVersionsResolver.model

sealed interface PluginError {
    data object NotFound : PluginError
    data object InvalidRequest: PluginError
}