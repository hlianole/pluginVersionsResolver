package com.hlianole.jetbrains.internship.pluginVersionsResolver.service

import com.hlianole.jetbrains.internship.pluginVersionsResolver.dto.PlatformCreateRequest
import com.hlianole.jetbrains.internship.pluginVersionsResolver.model.Architecture
import com.hlianole.jetbrains.internship.pluginVersionsResolver.model.OS

object PluginRequestValidator {

    fun validateVersionName(versionName: String): Boolean {
        val regex = Regex("""^(\d+)\.(\d+)\.(\d+)(?:[-]([A-Za-z0-9]+(?:\.[A-Za-z0-9]+)*))?$""")
        return regex.matches(versionName)
    }

    fun validatePlatform(platform: PlatformCreateRequest?): Boolean {
        return if (platform == null){
            return true
        } else {
            validateOs(platform.os) && validateArch(platform.arch)
        }
    }

    private fun validateOs(os: String?): Boolean {
        return if (os == null){
            return true
        } else {
            OS.entries.map {
                it.name
            }.contains(os.uppercase())
        }
    }

    private fun validateArch(arch: String?): Boolean {
        return if (arch == null){
            return true
        } else {
            Architecture.entries.map {
                it.name
            }.contains(arch.uppercase())
        }
    }

}