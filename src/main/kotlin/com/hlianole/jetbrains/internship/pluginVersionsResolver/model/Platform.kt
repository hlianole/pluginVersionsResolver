package com.hlianole.jetbrains.internship.pluginVersionsResolver.model

data class Platform (
    val os: OS?,
    val arch: Architecture?,
)

enum class OS {
    MACOS, WINDOWS, LINUX
}

enum class Architecture {
    X64, X86, ARM64
}