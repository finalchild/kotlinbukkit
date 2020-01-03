/*
 * Copyright 2017-2020 Bak Jaeon (finalchild) and Ranol
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.finalchild.kotlinbukkit

import me.finalchild.kotlinbukkit.updater.getLatestRelease
import me.finalchild.kotlinbukkit.util.delegate
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

/**
 * Bukkit plugin to support writing scripts and plugins using Kotlin.
 * This class is singleton and the instance is created by Bukkit.
 * To get the instance, use [kotlinBukkit].
 */
class KotlinBukkit : JavaPlugin(), Listener {

    val checkUpdates: Boolean by config.delegate("check-updates")

    override fun onEnable() {
        if (_kotlinBukkit != null) {
            throw IllegalStateException()
        }
        _kotlinBukkit = this
        saveDefaultConfig()
        if (!isSnapshot && checkUpdates) {
            val latestRelease = getLatestRelease()
            if (latestRelease != null && description.version != latestRelease.version
                && (description.version.startsWith('v')
                        || !latestRelease.version.startsWith('v')
                        || description.version != latestRelease.version.substring(1))) {
                logger.info("A new release(${latestRelease.version}) of kotlinbukkit is found!")
                logger.info("Update now: ${latestRelease.url}")
            }
        }
    }

    override fun onDisable() {
        if (_kotlinBukkit == this) {
            _kotlinBukkit = null
        }
    }

    val isSnapshot: Boolean
        get() = description.version.endsWith("-SNAPSHOT")

}

private var _kotlinBukkit: KotlinBukkit? = null

/**
 * Singleton instance of [KotlinBukkit]. Should be created by Bukkit.
 */
val kotlinBukkit: KotlinBukkit
    get() = _kotlinBukkit ?: throw IllegalStateException()
