package com.hlianole.jetbrains.internship.pluginVersionsResolver.controller

import org.springframework.http.ResponseEntity

abstract class BaseController {

    protected inline fun <T> withCatchBlock(
        block: () -> ResponseEntity<T>
    ): ResponseEntity<T> {
        return try {
            block()
        } catch (e: Exception) {
            e.printStackTrace()
            return ResponseEntity.internalServerError().build()
        }
    }

    protected inline fun <T> deletion(
        block: () -> Boolean
    ): ResponseEntity<T> {
        return withCatchBlock {
            val success = block()
            if (success) ResponseEntity.ok().build() else ResponseEntity.notFound().build()
        }
    }

}