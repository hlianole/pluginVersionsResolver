package com.hlianole.jetbrains.internship.pluginVersionsResolver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PluginVersionsResolverApplication

fun main(args: Array<String>) {
	runApplication<PluginVersionsResolverApplication>(*args)
}
