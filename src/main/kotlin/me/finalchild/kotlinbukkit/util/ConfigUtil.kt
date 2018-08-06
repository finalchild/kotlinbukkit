/*
 * Copyright 2017-2018 Bak Jaeon (finalchild) and Ranol
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

package me.finalchild.kotlinbukkit.util

import org.bukkit.configuration.ConfigurationSection
import kotlin.reflect.KProperty

operator fun <T> ConfigurationSection.getValue(thisRef: Any?, property: KProperty<*>): T = this[property.name] as T

operator fun ConfigurationSection.setValue(thisRef: Any?, property: KProperty<*>, value: Any?) {
    this[property.name] = value
}

fun ConfigurationSection.delegate(key: String): ConfigurationReceiver = ConfigurationReceiver(this, key)

class ConfigurationReceiver(val configurationSection: ConfigurationSection, val key: String) {

    operator fun <T> getValue(thisRef: Any?, property: KProperty<*>): T = configurationSection[key] as T

    operator fun <T> setValue(thisRef: Any?, property: KProperty<*>, value: Any?) {
        configurationSection[key] = value
    }

}
